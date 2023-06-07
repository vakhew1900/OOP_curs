package model;

import model.material.Glass;
import model.material.Metal;
import model.material.Plastic;
import model.material.Steel;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.ExclusivePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExclusivePlumberProductEndTest {


    @Test
    public void exclusiveMaterialEqualseMaterial(){

        Assertions.assertThrows(IllegalArgumentException.class, ()->{new ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Glass());});
    }

    @Test
    public void typeTest(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Plastic());

        Assertions.assertTrue(plumberProductEnd2.isCanConnected(plumberProductEnd));
    }

    @Test
    public void exclusiveEqualsMaterialInOtherEnd(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumberProductEnd2 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());

        Assertions.assertFalse(plumberProductEnd2.isCanConnected(plumberProductEnd));
    }
    @Test
    public void exclusiveMaterialIsSuperClassForMaterialInOtherEnd(){

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.east(), PlumberProductEnd.BIG_DIAMETER, new Steel());
        PlumberProductEnd plumberProductEnd2 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());

        Assertions.assertFalse(plumberProductEnd2.isCanConnected(plumberProductEnd));
    }


}
