package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.*;

public class PlumbingProductTest {


    private Cell cell;

    @BeforeEach
    private void createCell(){
        cell = new Cell(1, 1);
    }

//------------------------ тестирование заполнение водой  ---------------------------------------------

    @Test
    public void fillTypeTest(){

        PlumbingProduct plumbingProduct = new Drain(Direction.east(), cell);

        Assertions.assertFalse(plumbingProduct.isFilled());

        Water water = new Water();
        plumbingProduct.fill(water);
        Assertions.assertTrue(plumbingProduct.isFilled());
        Assertions.assertNotNull(water.getLastFillingPlumbingProduct());
    }

    @Test
    public void fillTwiceTest(){

        PlumbingProduct plumbingProduct = new Drain(Direction.east(), cell);

        Assertions.assertFalse(plumbingProduct.isFilled());

        Water water = new Water();
        Water water2 = new Water();
        plumbingProduct.fill(water);
        plumbingProduct.fill(water2);
        Assertions.assertTrue(plumbingProduct.isFilled());
        Assertions.assertNull(water.getLastFillingPlumbingProduct());
        Assertions.assertNotNull(water2.getLastFillingPlumbingProduct());
    }

    @Test
    public void fillNull(){
        PlumbingProduct plumbingProduct = new Drain(Direction.east(), cell);

        plumbingProduct.fill(null);

        Assertions.assertFalse(plumbingProduct.isFilled());
    }



}
