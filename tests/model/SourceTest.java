package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;

public class SourceTest {


    private Cell cell;

    @BeforeEach
    private void createCell(){
        cell = new Cell(1, 1);
    }


    //------------------- Конструктор тест ---------------------------------------
    @Test
    public void constructorTest(){

        PlumbingProduct plumbingProduct = new Source(Direction.east(), cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(Direction.east()));
    }

    @Test
    public void directionIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Source(null, cell));
    }

    @Test
    public void cellIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Source(Direction.east(), null));
    }

}