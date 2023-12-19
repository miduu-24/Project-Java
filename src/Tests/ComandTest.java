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
            assert comand.getDate().equals("12.12.2020");
            comand.setDate("13.12.2020");
            assert comand.getDate().equals("13.12.2020");
            assert comand.toString().equals("Comand{id='2', cakes='[Cake{id='1', type='Chocolate'}]', date='13.12.2020'}");
            Comand comand1 = new Comand();
            try{
                Comand comand3 = new Comand(4, new ArrayList<Cake>(), "12.12.2020");
                assert false;
            } catch (ComandException e) {
                assert true;
            }


    }

}
