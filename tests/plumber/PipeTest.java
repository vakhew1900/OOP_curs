package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.plumber_product.Pipe;
import plumber.plumber_product.PlumbingProduct;

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

        Set<Direction> ends = new HashSet(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct = new Pipe(ends, cell);

        Assertions.assertTrue(plumbingProduct.hasEnd(Direction.south()));
        Assertions.assertTrue(plumbingProduct.hasEnd(Direction.west()));
    }

    @Test
    public void directionSetIsNull(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Pipe(null, cell));
    }


    //---------------------------rotate ------------------------------------------

    @Test
    public void rotate_TypeTest(){

        Set<Direction> ends = new HashSet(List.of(new Direction[]{Direction.south(), Direction.west()}));
        Pipe pipe = new Pipe(ends, cell);

        pipe.rotate();

        Assertions.assertTrue(pipe.hasEnd(Direction.west()));
        Assertions.assertTrue(pipe.hasEnd(Direction.north()));

    }

    @Test
    public void rotate_TypeTest2(){

        Set<Direction> ends = new HashSet(List.of(new Direction[]{Direction.south(), Direction.north()}));
        Pipe pipe = new Pipe(ends, cell);

        pipe.rotate();

        Assertions.assertTrue(pipe.hasEnd(Direction.west()));
        Assertions.assertTrue(pipe.hasEnd(Direction.east()));

    }
}
