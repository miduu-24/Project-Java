package Tests;

import Service.ServiceCake;
import Service.ServiceComand;
import Service.ServiceException;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    @Test
    public void ServiceCakeTest() throws ServiceException {
        ServiceCake serviceCake = new ServiceCake();
        serviceCake.addCake("cake11");
        assert serviceCake.getAllCakes().size() == 11;
        serviceCake.updateCake(1, "cake2");
        assert serviceCake.getAllCakes().size() == 11;
        serviceCake.removeCake(8);
        assert serviceCake.getAllCakes().size() == 10;
    }

    @Test
    public void ServiceComandTest() throws ServiceException {
        ServiceComand serviceComand = new ServiceComand();
        ServiceCake serviceCake = new ServiceCake();
        serviceCake.addCake("cake1");
        serviceComand.addComand(serviceCake.getAllCakes(), "12.12.2020");
        assert serviceComand.getAllComands().size() == 1;
        serviceComand.updateComand(1, serviceCake.getAllCakes(), "12.12.2023");
        assert serviceComand.getAllComands().size() == 1;
        serviceComand.removeComand(4);
        assert serviceComand.getAllComands().size() == 0;
    }
}
