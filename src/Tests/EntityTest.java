package Tests;

import Domain.Cake;
import Domain.Entity;
import org.junit.jupiter.api.Test;

public class EntityTest {
    @Test
    public void testEntity(){
        Entity entity = new Cake(1, "Chocolate");
        assert entity.getId() == 1;
        assert entity.toString().equals("Cake{id='1', type='Chocolate'}");
    }
}
