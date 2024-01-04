import Domain.*;
import Repository.*;
import Service.ServiceCake;
import Service.ServiceComand;
import Service.ServiceException;
import UI.UI;
import UI.JavaFX;

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

       if (Objects.equals(setari.getRepoType(), "sql")) {
            repoCake = new CakeSQLRepository(setari.getRepoFile1());
            repoComand = new ComandSQLRepository(setari.getRepoFile2());
       }

        ServiceCake serviceCake = new ServiceCake(repoCake);
        ServiceComand serviceComand = new ServiceComand(repoCake, repoComand);

        if(Objects.equals(setari.getStart(),"javafx"))
        {
            JavaFX.setCake(serviceCake);
            JavaFX.setServiceComand(serviceComand);
            JavaFX.launch(JavaFX.class);
        }
        else
        {
            UI ui = new UI(serviceCake, serviceComand);
            ui.run();
        }
    }
}