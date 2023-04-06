package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.plumber_product.Drain;
import plumber.plumber_product.Pipe;
import plumber.plumber_product.PlumbingProduct;
import plumber.plumber_product.Source;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaterTest {

    GameField gameField;
    private PlumbingProduct source(){

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getContent();
            if (plumbingProduct instanceof Source) {
                return plumbingProduct;
            }

        }

        return null;
    }

    //----------------- Конструктор Тест ---------------


    @Test
    public void constructor_TypeTest() {

        Water water = new Water(3000);
        Water water1 = new Water();
        Water water2 = new Water(1);

        Assertions.assertEquals(3000, water.timeout());
        Assertions.assertEquals(2000, water1.timeout());
        Assertions.assertEquals(1, water2.timeout());

    }

    @Test
    public void constructor_IllegalArgumentTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Water(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Water(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Water(-1000));
    }


    //------------------------- Тестировние nextPlumbingProduct -------------------------------------------

    Cell cell1;
    Cell cell2;

    PlumbingProduct plumbingProduct1;
    PlumbingProduct plumbingProduct2;
    @BeforeEach
    public void createSurrounding(){
        cell1 = new Cell(1, 1);
        cell2 = new Cell(2, 1);

        Set<Direction> set1 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        Set<Direction> set2 = new HashSet<>(List.of(new Direction[]{Direction.east(), Direction.west()}));
        plumbingProduct1 = new Pipe(set1, cell1);
        plumbingProduct2 = new Pipe(set2, cell2);


    }


    @Test
    public void nextPlumbingProduct_TypeTest(){

        cell1.setNeighbor(Direction.north(), cell2);

        Water water = new Water(1);
        plumbingProduct2.fill(water);
        water.nextPlumbingProduct(plumbingProduct1);
        Assertions.assertEquals(plumbingProduct1, water.getLastFillingPlumbingProduct());
    }

    @Test
    public void nextPlumbingProduct_PlumbingProductFilledTwiceSameWaterTest(){

        Water water = new Water(1);

        water.nextPlumbingProduct(plumbingProduct2);
        water.nextPlumbingProduct(plumbingProduct2);
        Assertions.assertEquals(plumbingProduct2, water.getLastFillingPlumbingProduct());
    }


    @Test
    public void nextPlumbingProduct_WithTwoPlumbingProductTest(){

        Water water = new Water(1);

        water.nextPlumbingProduct(plumbingProduct2);
        water.nextPlumbingProduct(plumbingProduct1);
        Assertions.assertEquals(plumbingProduct1, water.getLastFillingPlumbingProduct());
        Assertions.assertTrue(plumbingProduct1.isFilled());
        Assertions.assertTrue(plumbingProduct2.isFilled());
    }


    // тестирование flow

    @Test
    public void flow_typeTest() throws InterruptedException {

        gameField = new GameField(3, 3);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();


        Water water = new Water();
        source().fill(water);

        while (water.isStopped() == false){
            water.flow();
        }

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);

    }


    @Test
    public void flow_bigFieldTest() throws InterruptedException {

        gameField = new GameField(100, 100);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        Water water = new Water();
        source().fill(water);

        while (water.isStopped() == false){
            water.flow();
        }

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);

    }


    @Test
    public void flow_smallField(){

        gameField = new GameField(1, 2);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        Water water = new Water(1);
        source().fill(water);

        while (water.isStopped() == false){
            water.flow();
        }

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);
    }


    //-----------------------------------------TimerTest---------------------------------------------

    @Test
    public void start_typeTest() throws InterruptedException {

        gameField = new GameField(3, 3);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();


        Water water = new Water(1);
        source().fill(water);
        water.start();

        Thread.sleep(1000);

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);

    }

    @Test
    public void start_bigTimeOutTest() throws InterruptedException {

        gameField = new GameField(3, 3);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();


        Water water = new Water(100);
        source().fill(water);
        water.start();

        Thread.sleep(2000);

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);

    }


    @Test
    public void start_bigFieldTest() throws InterruptedException {

        gameField = new GameField(20, 20);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        Water water = new Water(1);
        source().fill(water);
        water.start();

        Thread.sleep(7000);

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);

    }


    @Test
    public void start_smallField() throws InterruptedException {

        gameField = new GameField(1, 2);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        Water water = new Water(1);
        source().fill(water);
        water.start();

        Thread.sleep(100);

        Assertions.assertTrue(source().water().getLastFillingPlumbingProduct() instanceof Drain);
    }





}
