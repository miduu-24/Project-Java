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
        cakeRepository.add(new Cake(11, "cake1"));
        assert cakeRepository.findById(11).getType().equals("cake1");

        cakeRepository.remove(1);
        assert cakeRepository.findById(1) == null;

        cakeRepository.update(2, new Cake(2, "cake3"));
        assert cakeRepository.findById(2).getType().equals("cake3");
    }

    @Test
    public void RepositoryComandTest() throws RepositoryException, ComandException {
        ComandRepository comandRepository = new ComandRepository();
        ArrayList<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake(11, "cake11"));
        comandRepository.add(new Comand(11, cakes, "12.12.2020"));
        assert comandRepository.findById(1).getCakes().size() == 6;
        ArrayList<Cake> cakes2 = new ArrayList<>();
        cakes2.add(new Cake(2, "cake2"));
        comandRepository.update(1, new Comand(30, cakes2, "12.12.2020"));
        assert comandRepository.findById(1).getCakes().size() == 1;

        comandRepository.remove(30);
        assert comandRepository.getAll().size() == 5;

    }

}
