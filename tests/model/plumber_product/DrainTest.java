package model.plumber_product;

import model.Cell;
import model.Direction;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.plumber_product.Drain;
import model.plumber_product.PlumbingProduct;

public class DrainTest {


    private Cell cell;

    @BeforeEach
    private void createCell(){
        cell = new Cell(1, 1);
    }


    //------------------- Конструктор тест ---------------------------------------
    @Test
    public void constructorTest(){

        AbstractPlumberProductEnd plumberProductEnd = new SimplePlumberProductEnd(Direction.east());
        PlumbingProduct plumbingProduct = new Drain(plumberProductEnd, cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(plumberProductEnd));
    }

    @Test
    public void directionIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Drain(null, cell));
    }

    @Test
    public void cellIsNull(){

        AbstractPlumberProductEnd plumberProductEnd = new SimplePlumberProductEnd(Direction.east());
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Drain(plumberProductEnd, null));
    }


}
