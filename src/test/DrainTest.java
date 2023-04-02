package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.Cell;
import plumber.Direction;
import plumber.Drain;
import plumber.PlumbingProduct;

public class DrainTest {


    private Cell cell;

    @BeforeEach
    private void createCell(){
        cell = new Cell(1, 1);
    }


    //------------------- Конструктор тест ---------------------------------------
    @Test
    public void constructorTest(){

        PlumbingProduct plumbingProduct = new Drain(Direction.east(), cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(Direction.east()));
    }

    @Test
    public void directionIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Drain(null, cell));
    }

    @Test
    public void cellIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Drain(Direction.east(), null));
    }


}
