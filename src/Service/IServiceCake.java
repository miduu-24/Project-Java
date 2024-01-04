package Service;

import Domain.Cake;

import java.util.Collection;

public interface IServiceCake {

    int generateIdCake();
    void addCake(String type) throws ServiceException;
    void removeCake(int id) throws ServiceException;
    void updateCake(int id_1, String type) throws ServiceException;
    Collection getAllCakes();

    Cake findbyId(int id);


}
