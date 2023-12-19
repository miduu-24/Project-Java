package Tests;

import Domain.Cake;
import Domain.CakeConvert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CakeConvertTest {
    @Test
    void testToString() {
        Cake cake = new Cake(1, "cioco");
        CakeConvert cakeConvert = new CakeConvert();
        String expectedString = "1,cioco";
        assertEquals(expectedString, cakeConvert.toString(cake));
    }

    @Test
    void testFromString() {
        CakeConvert cakeConvert = new CakeConvert();
        String cakeString = "1,cioco";
        Cake expectedCake = new Cake(1, "cioco");
        assertEquals(expectedCake.getId(), cakeConvert.fromString(cakeString).getId());
    }
}
