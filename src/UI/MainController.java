package UI;

import Service.ServiceCake;
import Service.ServiceComand;

public class MainController {
    private ServiceCake serviceCake;
    private ServiceComand serviceComand;

    public void setServiceCake(ServiceCake serviceCake) {
        this.serviceCake = serviceCake;
    }

    public void setServiceComand(ServiceComand serviceComand) {
        this.serviceComand = serviceComand;
    }
}
