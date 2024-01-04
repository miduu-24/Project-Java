package Service;

import Domain.Cake;
import Domain.Comand;
import Repository.CakeRepository;
import Repository.ComandRepository;
import Repository.IRepository;

import java.util.*;

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

    @Override
    public ArrayList<String> getNumberOfCakes() {
        ArrayList<String> numberOfCakes = new ArrayList<>();
        for (Comand comand : comandRepository.getAll()) {
            int ok = 0;
            for (String number : numberOfCakes) {
                if (number.contains(comand.getDate())) {
                    int index = numberOfCakes.indexOf(number);
                    numberOfCakes.set(index, comand.getDate() + ": " + (Integer.parseInt(number.split(": ")[1]) + comand.getCakes().size()) + "");
                    ok = 1;
                }
            }
            if (ok == 0)
                numberOfCakes.add(comand.getDate() + ": " + comand.getCakes().size() + "");
        }
        ArrayList<String> sorted = Asort(numberOfCakes);
        return sorted;
    }

    @Override
    public ArrayList<String> getNumberOfCakesByEveryMonth() {
        ArrayList<String> numberOfCakes = new ArrayList<>();
        for (Comand comand : comandRepository.getAll()) {
            int ok = 0;
            String[] data = comand.getDate().split("\\.");
            String month = data[1];
            for (String number : numberOfCakes) {
                if (number.contains(month)) {
                    int index = numberOfCakes.indexOf(number);
                    numberOfCakes.set(index, month + ": " + (Integer.parseInt(number.split(": ")[1]) + comand.getCakes().size()) + "");
                    ok = 1;
                }
            }
            if (ok == 0)
                numberOfCakes.add(month + ": " + comand.getCakes().size() + "");
        }
        ArrayList<String> sorted = Asort(numberOfCakes);
        return sorted;
    }

    private ArrayList<String> Asort(ArrayList<String> numberOfCakes) {
        ArrayList<String> sorted = new ArrayList<>();
        while (numberOfCakes.size() != 0) {
            int max = 0;
            int index = 0;
            for (int i = 0; i < numberOfCakes.size(); i++) {
                if (Integer.parseInt(numberOfCakes.get(i).split(": ")[1]) > max) {
                    max = Integer.parseInt(numberOfCakes.get(i).split(": ")[1]);
                    index = i;
                }
            }
            sorted.add(numberOfCakes.get(index));
            numberOfCakes.remove(index);
        }
        return sorted;
    }

    @Override
    public ArrayList<String> getNumberOfComandsByEveryCake(){
        ArrayList<String> numberOfComands = new ArrayList<>();
        for (Comand comand : comandRepository.getAll()) {
            for (Cake cake : comand.getCakes()) {
                int ok = 0;
                for (String number : numberOfComands) {
                    if (number.contains(cake.getType())) {
                        int index = numberOfComands.indexOf(number);
                        numberOfComands.set(index, cake.getType() + ": " + (Integer.parseInt(number.split(": ")[1]) + 1) + "");
                        ok = 1;
                    }
                }
                if (ok == 0)
                    numberOfComands.add(cake.getType() + ": " + 1 + "");
            }
        }
        ArrayList<String> sorted = Asort(numberOfComands);
        return sorted;
    }

}
