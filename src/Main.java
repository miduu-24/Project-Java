import Domain.*;
import Repository.*;
import Service.ServiceCake;
import Service.ServiceComand;
import Service.ServiceException;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws ServiceException, RepositoryException, ComandException {
        IRepository<Cake> repoCake = null;
        IRepository<Comand> repoComand = null;
        CakeConvert cakeconv = new CakeConvert();
        ComandConvert comandconv = new ComandConvert();

        Settings setari = Settings.getInstance();
       if (Objects.equals(setari.getRepoType(), "memory")) {
            repoCake = new CakeRepository();
            repoComand = new ComandRepository();
       }
       if (Objects.equals(setari.getRepoType(), "text")) {
            repoCake = new TextRepository<>(setari.getRepoFile1(), cakeconv);
            repoComand = new TextRepository<>(setari.getRepoFile2(), comandconv);
       }

       if (Objects.equals(setari.getRepoType(), "binary")) {
            repoCake = new BinaryRepository<>(setari.getRepoFile1());
            repoComand = new BinaryRepository<>(setari.getRepoFile2());
       }



        ServiceCake serviceCake = new ServiceCake(repoCake);
        ServiceComand serviceComand = new ServiceComand(repoCake, repoComand);
        UI ui = new UI(serviceCake, serviceComand);
        ui.run();
    }
}