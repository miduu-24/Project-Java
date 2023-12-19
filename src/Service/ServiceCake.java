package Service;

import Domain.Cake;
import Repository.CakeRepository;
import Repository.ComandRepository;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.Random;

public class ServiceCake implements IServiceCake{

    private final IRepository<Cake> cakeRepository;

    public ServiceCake(IRepository<Cake> cakeRepository) {
        this.cakeRepository = cakeRepository;
    }
    @Override
    public int generateIdCake() {
        Random random = new Random();
        boolean ok = false;
        int id = 0;
        while (!ok) {
            id = random.nextInt(100000);
            ok = true;
            if(cakeRepository.findById(id) != null) {
                ok = false;
            }
        }
        return id;
    }
    @Override
    public void addCake(String type) throws ServiceException {
        try {
            int id = generateIdCake();
            Cake cake = new Cake(id, type);
            cakeRepository.add(cake);
        } catch (Exception e) {
            throw new ServiceException("Error adding cake: " + e);
        }
    }
    @Override
    public void removeCake(int id) throws ServiceException {
        try {
            cakeRepository.remove(id);
        } catch (Exception e) {
            throw new ServiceException("Error removing cake: " + e);
        }

    }
    @Override
    public void updateCake(int id_1, String type) throws ServiceException {
        try {
            if (cakeRepository.findById(id_1) == null) {
                throw new ServiceException("Cake with id " + id_1 + " does not exist!");
            }
            int id_2 = generateIdCake();
            Cake cake = new Cake(id_2, type);
            cakeRepository.update(id_1, cake);
        } catch (Exception e) {
            throw new ServiceException("Error updating cake: " + e);
        }
    }
    @Override
    public ArrayList<Cake> getAllCakes() {
        return (ArrayList<Cake>) cakeRepository.getAll();
    }
}
