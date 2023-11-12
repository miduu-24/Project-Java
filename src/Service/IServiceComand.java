package Service;

import Domain.Cake;
import Domain.Comand;

import java.util.ArrayList;
import java.util.Collection;

public interface IServiceComand {
    int generateIdComand();
    public ArrayList<Cake> createCakeList(int[] ids) throws ServiceException;
    void addComand(ArrayList<Cake> cakes, String date) throws ServiceException;
    void removeComand(int id) throws ServiceException;
    void updateComand(int id, ArrayList<Cake> cakes, String date) throws ServiceException;
    void addCakeToComand(int comandId, int cakeid) throws ServiceException;
    void removeCakeFromComand(int comandId, int cakeId) throws ServiceException;
    Collection<Comand> getAllComands();
}
