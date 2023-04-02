package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.*;

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

}
