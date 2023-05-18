package model;

import model.material.Metal;
import model.material.Plastic;
import model.plumber_product_end.BigPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SmallPlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlumberPipeEndTest {

    @Test
    public void EqualsTypeTest(){
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.south(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.south(), new Metal());
        Assertions.assertEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentDirectionTest(){
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.north(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.south(), new Metal());
        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentMaterialTest(){
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.north(), new Plastic());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.south(), new Metal());
        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void differentDiameter(){
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.south(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new BigPlumberProductEnd(Direction.south(), new Metal());
        Assertions.assertNotEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void rotateTypeTest(){
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.north(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.east(), new Metal());

        plumberProductEnd.rotate();
        Assertions.assertEquals(plumberProductEnd2, plumberProductEnd);
    }

    @Test
    public void oppositeTypeTest()  {
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.north(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.south(), new Metal());

        Assertions.assertEquals(plumberProductEnd2, plumberProductEnd.opposite());
    }

    @Test
    public void oppositeTypeTest2()  {
        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.east(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new SmallPlumberProductEnd(Direction.west(), new Metal());

        Assertions.assertEquals(plumberProductEnd2, plumberProductEnd.opposite());
    }

}
