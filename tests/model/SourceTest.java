package model;

import model.material.Metal;
import model.plumber_product_end.PlumberProductEnd;
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

        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.east(), new Metal());
        PlumbingProduct plumbingProduct = new Source(plumberProductEnd, cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(plumberProductEnd));
    }

    @Test
    public void directionIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Source(null, cell));
    }

    @Test
    public void cellIsNull(){

        PlumberProductEnd plumberProductEnd = new SmallPlumberProductEnd(Direction.east(), new Metal());
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Source(plumberProductEnd, null));
    }

}
