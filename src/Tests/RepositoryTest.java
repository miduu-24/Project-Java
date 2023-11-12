package Tests;

import Domain.Cake;
import Domain.Comand;
import Domain.ComandException;
import Repository.CakeRepository;
import Repository.ComandRepository;
import Repository.RepositoryException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RepositoryTest {

    @Test
    public void RepositoryCakeTest() throws RepositoryException {
        CakeRepository cakeRepository = new CakeRepository();

        cakeRepository.add(new Cake(1, "cake1"));
        cakeRepository.add(new Cake(2, "cake2"));
        assert cakeRepository.findById(1).getType().equals("cake1");

        cakeRepository.remove(1);
        assert cakeRepository.findById(1) == null;

        cakeRepository.update(2, new Cake(2, "cake3"));
        assert cakeRepository.findById(2).getType().equals("cake3");
    }

    @Test
    public void RepositoryComandTest() throws RepositoryException, ComandException {
        ComandRepository comandRepository = new ComandRepository();
        ArrayList<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake(1, "cake1"));

        comandRepository.add(new Comand(1, cakes, "12.12.2020"));
        assert comandRepository.findById(1).getCakes().size() == 1;

        ArrayList<Cake> cakes2 = new ArrayList<>();
        cakes2.add(new Cake(2, "cake2"));
        comandRepository.update(1, new Comand(3, cakes2, "12.12.2020"));
        assert comandRepository.findById(1).getCakes().size() == 1;

        comandRepository.remove(1);
        assert comandRepository.findById(1) == null;

    }

}
