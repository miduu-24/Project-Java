package Repository;

import Domain.Cake;
import Domain.Comand;
import Domain.ComandException;

import java.util.ArrayList;

public class ComandRepository extends MemoryRepository<Comand> {

    private void mokaData() throws RepositoryException, ComandException {
        ArrayList<Cake> cakes1 = new ArrayList<>();
        cakes1.add(new Cake(1, "cake1"));
        add(new Comand(1, cakes1, "date1"));

        ArrayList<Cake> cakes2 = new ArrayList<>();
        cakes2.add(new Cake(2, "cake2"));
        cakes2.add(new Cake(8, "cake8"));
        add(new Comand(2, cakes2, "date2"));

        ArrayList<Cake> cakes3 = new ArrayList<>();
        cakes3.add(new Cake(3, "cake3"));
        cakes3.add(new Cake(6, "cake6"));
        cakes3.add(new Cake(9, "cake9"));
        add(new Comand(3, cakes3, "date3"));

        ArrayList<Cake> cakes4 = new ArrayList<>();
        cakes4.add(new Cake(4, "cake4"));
        cakes4.add(new Cake(7, "cake7"));
        cakes4.add(new Cake(10, "cake10"));
        add(new Comand(4, cakes4, "date4"));

        ArrayList<Cake> cakes5 = new ArrayList<>();
        cakes5.add(new Cake(5, "cake5"));
        add(new Comand(5, cakes5, "date5"));
    }
    public ComandRepository() {
        super();
        try {
            mokaData();
        } catch (RepositoryException | ComandException e) {
            throw new RuntimeException(e);
        }
    }
}
