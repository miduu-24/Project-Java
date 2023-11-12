package Tests;
import Domain.Cake;
import Domain.Comand;
import Domain.ComandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ComandTest {

    @Test
    public void TestComand() throws ComandException {
            ArrayList<Cake> cakes = new ArrayList<>();
            cakes.add(new Cake(1, "Chocolate"));
            Comand comand = new Comand(2, cakes, "12.12.2020");
            assert comand.getId() == 2;
            assert comand.getCakes().size() == 1;
    }

}
