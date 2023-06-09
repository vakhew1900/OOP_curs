package model.plumber_product;

import model.Cell;
import model.Direction;
import model.Water;
import model.events.PlumberProductFilledActionEvent;
import model.events.PlumberProductFilledActionListener;
import model.material.Glass;
import model.material.Metal;
import model.material.Plastic;
import model.material.Steel;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.plumber_product.Drain;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;

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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.north()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.north()),  new SimplePlumberProductEnd(Direction.west())}));
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


        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Drain(new SimplePlumberProductEnd(Direction.north()), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_WithDrainResultIsFalse(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.east(), cell2);

        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Drain( new SimplePlumberProductEnd(Direction.west()), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_WithSource(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Drain( new SimplePlumberProductEnd(Direction.north()), cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnected_SourceResultIsFalse(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);


        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Source(new SimplePlumberProductEnd(Direction.west()), cell1);
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.south()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.south())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
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

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        plumbingProduct1.fill(new Water());
        plumbingProduct2.fill(new Water());

        Assertions.assertFalse(plumbingProduct1.isCanFilled(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isCanFilled(plumbingProduct1));
    }

    //----------------------fill тест ---------------------------------

    @Test
    public void fill_PlumbingProductIsFilledTwiceAnotherWater(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Water water = new Water(1);
        Water water2 = new Water(1);

        plumbingProduct2.fill(water);
        plumbingProduct2.fill(water2);
        Assertions.assertEquals(plumbingProduct2, water.getLastFillingPlumbingProduct());
        Assertions.assertNull(water2.getLastFillingPlumbingProduct());
    }

    @Test
    public void fill_PlumbingProductIsFilledTwiceSameWater(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        Set<AbstractPlumberProductEnd> set1 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        Set<AbstractPlumberProductEnd> set2 = new HashSet(List.of(new AbstractPlumberProductEnd[]{ new SimplePlumberProductEnd(Direction.east()),  new SimplePlumberProductEnd(Direction.west())}));
        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Water water = new Water(1);
        Water water2 = new Water(1);

        plumbingProduct2.fill(water);
        plumbingProduct2.fill(water);
        Assertions.assertEquals(plumbingProduct2, water.getLastFillingPlumbingProduct());
    }

    @Test
    public void ListenerTest(){
         PlumbingProduct product = new Drain(new SimplePlumberProductEnd(Direction.east()), new Cell(1, 2));
         MydPlumberProductFilledActionListener tmp = new MydPlumberProductFilledActionListener();
         product.addPlumberProductFilledActionListener(tmp);

         int expectedX = tmp.x + 1;

         product.firePlumberProductAction();

         Assertions.assertEquals(expectedX, tmp.x);
         product.removeFlowActionListener(tmp);
         product.firePlumberProductAction();
         Assertions.assertEquals(expectedX, tmp.x);

    }

    private class MydPlumberProductFilledActionListener implements PlumberProductFilledActionListener{

        private int x;

        @Override
        public void plumberProductFilled(PlumberProductFilledActionEvent plumberProductFilledActionEvent) {
            x++;
        }
    }

    @Test
    public  void isConnectedWithExclusive_TypeTest(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<AbstractPlumberProductEnd> set1 = new HashSet<>();
        Set<AbstractPlumberProductEnd> set2 = new HashSet<>();

        PlumberProductEnd plumberProductEnd1 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.north(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());

        set1.add(plumberProductEnd1);
        set1.add(plumberProductEnd2);

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.south(), PlumberProductEnd.BIG_DIAMETER, new Plastic());
        PlumberProductEnd plumbingProductEnd3 = new PlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Steel());

        set2.add(plumbingProductEnd3);
        set2.add(plumberProductEnd);

        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertTrue(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertTrue(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnectedWithExclusive_NotConnected_Test(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<AbstractPlumberProductEnd> set1 = new HashSet<>();
        Set<AbstractPlumberProductEnd> set2 = new HashSet<>();

        PlumberProductEnd plumberProductEnd1 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.north(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());

        set1.add(plumberProductEnd1);
        set1.add(plumberProductEnd2);

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.south(), PlumberProductEnd.BIG_DIAMETER, new Metal());
        PlumberProductEnd plumbingProductEnd3 = new PlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Steel());

        set2.add(plumbingProductEnd3);
        set2.add(plumberProductEnd);

        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }

    @Test
    public  void isConnectedWithExclusive_NotConnected2_TypeTest(){

        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);

        cell1.setNeighbor(Direction.north(), cell2);

        Set<AbstractPlumberProductEnd> set1 = new HashSet<>();
        Set<AbstractPlumberProductEnd> set2 = new HashSet<>();

        PlumberProductEnd plumberProductEnd1 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());
        PlumberProductEnd plumberProductEnd2 = new model.plumber_product_end.ExclusivePlumberProductEnd(Direction.north(), PlumberProductEnd.BIG_DIAMETER, new Glass(), new Metal());

        set1.add(plumberProductEnd1);
        set1.add(plumberProductEnd2);

        PlumberProductEnd plumberProductEnd = new PlumberProductEnd(Direction.south(), PlumberProductEnd.SMALL_DIAMETER, new Plastic());
        PlumberProductEnd plumbingProductEnd3 = new PlumberProductEnd(Direction.west(), PlumberProductEnd.BIG_DIAMETER, new Steel());

        set2.add(plumbingProductEnd3);
        set2.add(plumberProductEnd);

        PlumbingProduct plumbingProduct1 = new Pipe(set1, cell1);
        PlumbingProduct plumbingProduct2 = new Pipe(set2, cell2);

        Assertions.assertFalse(plumbingProduct1.isConnected(plumbingProduct2));
        Assertions.assertFalse(plumbingProduct2.isConnected(plumbingProduct1));
    }
}