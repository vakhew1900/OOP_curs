package model;

import model.material.Metal;
import model.material.Plastic;
import model.material.Steel;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlumberPipeEndTest {

    @Test
    public void constructorTypeTest(){

        PlumberProductEnd productEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.SMALL_DIAMETER, new Metal() );

        Assertions.assertEquals(Direction.east(), productEnd.direction());
        Assertions.assertEquals(PlumberProductEnd.SMALL_DIAMETER, productEnd.diameter());
        Assertions.assertEquals(new Metal(), productEnd.material());
    }

    @Test
    public void equalsTypeTest(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.SMALL_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new PlumberProductEnd(Direction.east(), PlumberProductEnd.SMALL_DIAMETER, new Metal());

        Assertions.assertEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentDiameterTest(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new PlumberProductEnd(Direction.east(), PlumberProductEnd.SMALL_DIAMETER, new Metal());

        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentMaterialTest(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Steel());

        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void negativeDiameterTest(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> { new PlumberProductEnd(Direction.east(), -1, new Metal());});
    }

    @Test
    public void differentMaterial2Test(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Plastic());

        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentDirectionTest(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new PlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Plastic());

        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void compareWithOtherObject(){
        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        Assertions.assertFalse(plumberProductEnd.equals(new Object()));
        Assertions.assertFalse(plumberProductEnd.equals(new SimplePlumberProductEnd(Direction.east())));
    }

}
