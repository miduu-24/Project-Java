package Repository;

import Domain.Cake;

public class CakeRepository extends MemoryRepository<Cake>{

    private void mokaData() throws RepositoryException {
        add(new Cake(1, "cake1"));
        add(new Cake(2, "cake2"));
        add(new Cake(3, "cake3"));
        add(new Cake(4, "cake4"));
        add(new Cake(5, "cake5"));
        add(new Cake(6, "cake6"));
        add(new Cake(7, "cake7"));
        add(new Cake(8, "cake8"));
        add(new Cake(9, "cake9"));
        add(new Cake(10, "cake10"));
    }
    public CakeRepository(){
        super();
        try {
            mokaData();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
