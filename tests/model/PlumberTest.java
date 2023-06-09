package model;

import model.plumber_product_end.AbstractPlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.plumber_product.Drain;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlumberTest {

    private GameField gameField;
    private Plumber plumber;


    /** -------------- createPipeline ------------------------------ */

    private PlumbingProduct getLastPipeLineElement(PlumbingProduct source){
        List<PlumbingProduct> plumbingProducts = new ArrayList<>();
        plumbingProducts.add(source);

        PlumbingProduct current = source;

        boolean f = true;

        while (f){

            boolean isUnVisited = false;
            for(AbstractPlumberProductEnd plumberProductEnd : current.getEnds()){

                Direction direction = plumberProductEnd.direction();
                PlumbingProduct neighbor = current.neighbor(direction);

                if (neighbor != null && !plumbingProducts.contains(neighbor) && current.isConnected(neighbor)){
                    current = neighbor;
                    isUnVisited = true;
                    plumbingProducts.add(neighbor);
                    break;
                }



            }

            if (isUnVisited == false){
                System.out.println(current);
                for(AbstractPlumberProductEnd plumberProductEnd : current.getEnds()) {

                    Direction direction = plumberProductEnd.direction();
                    PlumbingProduct neighbor = current.neighbor(direction);
                    System.out.println(neighbor);

                    if (neighbor == null){
                        continue;
                    }

                    if(plumbingProducts.contains(neighbor) == true){
                        boolean tmp = current.isConnected(neighbor);
                        PlumbingProduct drain = drain();
                        System.out.println("fff");
                    }
                    else {
                        boolean tmp = current.isConnected(neighbor);
                    }
                }
            }
            f = f && isUnVisited;
        }

        return  current;
    }

    private PlumbingProduct source(){

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getPlumbingProduct();
            if (plumbingProduct instanceof Source) {
                return plumbingProduct;
            }

        }

        return null;
    }

    private PlumbingProduct drain(){

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, gameField.width() - 1).getPlumbingProduct();
            if (plumbingProduct instanceof Drain) {
                return plumbingProduct;
            }

        }

        return null;
    }


    @Test
    public void createPipeLine_TypeTest(){

        gameField = new GameField(4, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void createPipeLine_TypeTest2(){

        gameField = new GameField(6, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void createPipeLine_BigField(){

        gameField = new GameField(50, 50);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void createPipeLine_SmallField(){

        gameField = new GameField(1, 2);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void createPipeLine_SmallField2(){

        /// фикс проверка на то что finish и start не равны между собой
        gameField = new GameField(2, 1);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void createPipeLine_VerySmallField(){
        gameField = new GameField(1, 1);
        Plumber plumber = new Plumber((gameField));
        Assertions.assertThrows(IllegalArgumentException.class, () -> plumber.createPipeline());
    }


    //-------------- shufflePipeline


    @Test
    public void shufflePipeline_TypeTest(){

        gameField = new GameField(4, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();
        plumber.shufflePipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }


    @Test
    public void shufflePipeline_TypeTest2(){

        gameField = new GameField(6, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();
        plumber.shufflePipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }

    @Test
    public void shufflePipeline_BigField(){

        gameField = new GameField(50, 50);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();
        plumber.shufflePipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }

    @Test
    public void shufflePipeline_SmallField(){

        gameField = new GameField(1, 2);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();
        plumber.shufflePipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }

    @Test
    public void shufflePipeline_SmallField2(){

        /// фикс проверка на то что finish и start не равны между собой
        gameField = new GameField(2, 1);
        Plumber plumber = new Plumber(gameField);
        plumber.createPipeline();
        plumber.shufflePipeline();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertTrue(last instanceof Drain);
    }


    //-------------------- configureTest ----------------------------------

    @Test
    public void configure_TypeTest(){

        gameField = new GameField(4, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.configure();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }


    @Test
    public void configure_TypeTest2(){

        gameField = new GameField(6, 4);
        Plumber plumber = new Plumber(gameField);
        plumber.configure();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }

    @Test
    public void configure_BigField(){

        gameField = new GameField(50, 100);
        Plumber plumber = new Plumber(gameField);
        plumber.configure();

        PlumbingProduct source = source();

        PlumbingProduct last = getLastPipeLineElement(source);

        Assertions.assertFalse(last instanceof Drain);
    }

    @Test
    public void configure_VerySmallField(){
        gameField = new GameField(1, 1);
        Plumber plumber = new Plumber((gameField));
        Assertions.assertThrows(IllegalArgumentException.class, () -> plumber.configure());
    }
}
