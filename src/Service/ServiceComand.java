package Service;

import Domain.Cake;
import Domain.Comand;
import Repository.CakeRepository;
import Repository.ComandRepository;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class ServiceComand implements IServiceComand {
    private final IRepository<Comand> comandRepository;
    private final IRepository<Cake> cakeRepository;

    public ServiceComand(IRepository<Cake> cakeRepository, IRepository<Comand> comandRepository) {
        this.comandRepository = comandRepository;
        this.cakeRepository = cakeRepository;
    }

    @Override
    public ArrayList<Cake> createCakeList(int[] ids) throws ServiceException {
        ArrayList<Cake> cakes = new ArrayList<>();
        for (int id : ids) {
            Cake cake = cakeRepository.findById(id);
            if (cake == null) {
                throw new ServiceException("Cake with id " + id + " does not exist!");
            }
            cakes.add(cake);
        }
        return cakes;
    }
    @Override
    public int generateIdComand(){
        Random random = new Random();
        boolean ok = false;
        int id = 0;
        while (!ok) {
            id = random.nextInt(100000);
            ok = true;
            if(comandRepository.findById(id) != null) {
                ok = false;
            }
        }
        return id;
    }

    @Override
    public void addComand(ArrayList<Cake> cakes, String data) throws ServiceException {
        try {
            int id = generateIdComand();
            comandRepository.add(new Comand(id, cakes, data));
        } catch (Exception e) {
            throw new ServiceException("Error adding comand: " + e);
        }

    }
    @Override
    public void removeComand(int id) throws ServiceException {
        try {
            comandRepository.remove(id);
        } catch (Exception e) {
            throw new ServiceException("Error removing comand: " + e);
        }
    }

    @Override
    public void updateComand(int id, ArrayList<Cake> cakes, String date) throws ServiceException {
        try {
            if (comandRepository.findById(id) == null) {
                throw new ServiceException("Comand with id " + id + " does not exist!");
            }
            int id_2 = generateIdComand();
            comandRepository.update(id, new Comand(id_2, cakes, date));
        } catch (Exception e) {
            throw new ServiceException("Error updating comand: " + e);
        }
    }


    @Override
    public ArrayList<Comand> getAllComands() {
        return (ArrayList<Comand>) comandRepository.getAll();
    }
}
