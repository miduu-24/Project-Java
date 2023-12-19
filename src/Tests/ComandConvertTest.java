package Tests;

import Domain.Cake;
import Domain.Comand;
import Domain.ComandConvert;
import Domain.ComandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ComandConvertTest {
    @Test
    public void testComandConvert() throws ComandException {
        Cake cake = new Cake(1, "Chocolate");
        ArrayList<Cake> cakes = new ArrayList<>();
        cakes.add(cake);
        Comand comand = new Comand(1, cakes, "22.11.2012");
        ComandConvert comandConvert = new ComandConvert();
        String expectedString = "1,[Cake{id='1', type='Chocolate'}],22.11.2012";
        assertEquals(expectedString, comandConvert.toString(comand));

        String line = "1,1,Chocolate,22.11.2012";
        Comand comand1 = comandConvert.fromString(line);
        assertEquals(comand1.getId(), 1);
    }
}
