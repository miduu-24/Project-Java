package Tests;

import Domain.Cake;
import Repository.CakeRepository;
import Repository.ComandRepository;
import Service.ServiceCake;
import Service.ServiceComand;
import Service.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

public class ServiceTest {
    @Test
    public void ServiceCakeTest() throws ServiceException {
        CakeRepository cakeRepository = new CakeRepository();
        ServiceCake serviceCake = new ServiceCake(cakeRepository);
        serviceCake.addCake("cake11");
        assert serviceCake.getAllCakes().size() == 11;
        serviceCake.updateCake(1, "cake2");
        assert serviceCake.getAllCakes().size() == 11;
        serviceCake.removeCake(8);
        assert serviceCake.getAllCakes().size() == 10;
    }

    @Test
    public void ServiceComandTest() throws ServiceException {
        CakeRepository cakeRepository = new CakeRepository();
        ComandRepository comandRepository = new ComandRepository();
        ServiceComand serviceComand = new ServiceComand(cakeRepository, comandRepository);
        ServiceCake serviceCake = new ServiceCake(cakeRepository);
        serviceCake.addCake("cake1");
        serviceComand.addComand(serviceCake.getAllCakes(), "12.12.2020");
        assert serviceComand.getAllComands().get(0).getCakes().size() == 1;
        serviceComand.updateComand(1, serviceCake.getAllCakes(), "12.12.2023");
        int id = serviceComand.getAllComands().get(0).getId();
        try {
            ArrayList<Cake> cakes = serviceComand.createCakeList(new int[]{1});
        } catch (ServiceException e) {
            assert e.getMessage().equals("Cake with id 1 does not exist!");
        }
        serviceComand.removeComand(id);}
}
