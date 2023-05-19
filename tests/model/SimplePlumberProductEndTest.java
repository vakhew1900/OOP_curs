package model;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimplePlumberProductEndTest {

    AbstractPlumberProductEnd northPlumberProductEnd = new SimplePlumberProductEnd(Direction.north());
    AbstractPlumberProductEnd eastPlumberProductEnd = new SimplePlumberProductEnd(Direction.east());
    AbstractPlumberProductEnd westPlumberProductEnd = new SimplePlumberProductEnd(Direction.west());
    AbstractPlumberProductEnd southPlumberProductEnd = new SimplePlumberProductEnd(Direction.south());

    @Test
    public void constructorTypeTest() {

        AbstractPlumberProductEnd plumberProductEnd = new SimplePlumberProductEnd(Direction.north());
        Assertions.assertEquals(Direction.north(), plumberProductEnd.direction());

        plumberProductEnd = new SimplePlumberProductEnd(Direction.east());
        Assertions.assertEquals(Direction.east(), plumberProductEnd.direction());

        plumberProductEnd = new SimplePlumberProductEnd(Direction.west());
        Assertions.assertEquals(Direction.west(), plumberProductEnd.direction());

        plumberProductEnd = new SimplePlumberProductEnd(Direction.south());
        Assertions.assertEquals(Direction.south(), plumberProductEnd.direction());
    }

    @Test
    public void rotateTest() {

        AbstractPlumberProductEnd simplePlumberProductEnd = new SimplePlumberProductEnd(Direction.north());

        simplePlumberProductEnd = simplePlumberProductEnd.rotate();
        Assertions.assertEquals(eastPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = simplePlumberProductEnd.rotate();
        Assertions.assertEquals(southPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = simplePlumberProductEnd.rotate();
        Assertions.assertEquals(westPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = simplePlumberProductEnd.rotate();
        Assertions.assertEquals(northPlumberProductEnd, simplePlumberProductEnd);
    }


    @Test
    public void opposite() {

        AbstractPlumberProductEnd simplePlumberProductEnd = new SimplePlumberProductEnd(Direction.north());

        simplePlumberProductEnd = simplePlumberProductEnd.opposite();
        Assertions.assertEquals(southPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = simplePlumberProductEnd.opposite();
        Assertions.assertEquals(northPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = new SimplePlumberProductEnd(Direction.east());

        simplePlumberProductEnd = simplePlumberProductEnd.opposite();
        Assertions.assertEquals(westPlumberProductEnd, simplePlumberProductEnd);

        simplePlumberProductEnd = simplePlumberProductEnd.opposite();
        Assertions.assertEquals(eastPlumberProductEnd, simplePlumberProductEnd);
    }


}
