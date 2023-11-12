package Tests;

import Domain.Cake;
import org.junit.jupiter.api.Test;

public class CakeTest {
    @Test
    public void testCake(){
        Cake cake = new Cake(1, "Chocolate");
        assert cake.getId() == 1;
        assert "Chocolate".equals(cake.getType());
    }
}
