package model;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PipeTest {


    //----------------------- конструктор тест ---------------------
    private Cell cell;

    @BeforeEach
    public void createCell(){
        cell = new Cell(1, 1);
    }

    @Test
    public void constructorTypeTest(){

        Set<AbstractPlumberProductEnd> ends = new HashSet(List.of(new SimplePlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct = new Pipe(ends, cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(new SimplePlumberProductEnd(Direction.west())));
        Assertions.assertTrue(plumbingProduct.hasEnd(new SimplePlumberProductEnd(Direction.south())));
    }

    @Test
    public void directionSetIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Pipe(null, cell));
    }


    //---------------------------rotate ------------------------------------------

    @Test
    public void rotate_TypeTest(){

        Set<AbstractPlumberProductEnd> ends = new HashSet(List.of(new SimplePlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        Pipe pipe = new Pipe(ends, cell);

        pipe.rotate();

        Assertions.assertTrue(pipe.hasEnd(new SimplePlumberProductEnd(Direction.north())));
        Assertions.assertTrue(pipe.hasEnd(new SimplePlumberProductEnd(Direction.west())));

    }

    @Test
    public void rotate_TypeTest2(){

        Set<AbstractPlumberProductEnd> ends = new HashSet(List.of(new SimplePlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.north())}));
        Pipe pipe = new Pipe(ends, cell);

        pipe.rotate();

        Assertions.assertTrue(pipe.hasEnd(new SimplePlumberProductEnd(Direction.east())));
        Assertions.assertTrue(pipe.hasEnd(new SimplePlumberProductEnd(Direction.west())));

    }
}
