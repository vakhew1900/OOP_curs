package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlumbingProductTest {


    private Cell cell;

    @BeforeEach
    private void createCell(){
        cell = new Cell(1, 1);
    }


//---------------------- поиск соседа ------------------------------------------------

    @Test
    public void neighbor_TypeTest(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertEquals(plumbingProduct2 ,plumbingProduct1.neighbor(Direction.east()));
        Assertions.assertEquals(plumbingProduct1 ,plumbingProduct2.neighbor(Direction.west()));
    }

    @Test
    public void neighbor_TypeTest2(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertEquals(plumbingProduct2 ,plumbingProduct1.neighbor(Direction.north()));
        Assertions.assertEquals(plumbingProduct1 ,plumbingProduct2.neighbor(Direction.south()));
    }

    @Test
    public void neighbor_CellIsClear(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
       // PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertNull(plumbingProduct1.neighbor(Direction.north()));
    }

//------------------------ тестирование соединения  ---------------------------------------------
    @Test
    public  void isConnected_TypeTest(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_TypeTest2(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.north(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_PipeAreNotConnected(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.north(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_WithDrain(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Drain(Direction.north(), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_WithDrainResultIsFalse(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Drain(Direction.west(), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_WithSource(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Drain(Direction.north(), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_SourceResultIsFalse(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.south(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Source(Direction.west(), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }


    // Проверить можно ли заполнить трубу


    @Test
    public void isCanFilledTest(){
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public void isCanFilledTest_plumbingProductAreNotConnected(){
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.south()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isCanFilled(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isCanFilled(plumbingProduct1));
    }

    @Test
    public void isCanFilledTest_OnePlumblingProductIsFilled(){
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.south()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        plumbingProduct1.fill(new Water());

        Assertions.assertTrue(plumbingProduct1.isCanFilled(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isCanFilled(plumbingProduct1));
    }


    @Test
    public void isCanFilledTest_BothPlumblingProductIsFilled(){
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(1, 2);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.south()}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        plumbingProduct1.fill(new Water());
        plumbingProduct1.fill(new Water());

        Assertions.assertFalse(plumbingProduct1.isCanFilled(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isCanFilled(plumbingProduct1));
    }
}
