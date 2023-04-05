package plumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plumber.plumber_product.Drain;
import plumber.plumber_product.Pipe;
import plumber.plumber_product.PlumbingProduct;
import plumber.plumber_product.Source;

import java.util.ArrayList;
import java.util.List;

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
            for(Direction direction : current.getEnds()){
                PlumbingProduct neighbor = current.neighbor(direction);

                if (neighbor != null && !plumbingProducts.contains(neighbor)){
                    current = neighbor;
                    isUnVisited = true;
                    plumbingProducts.add(neighbor);
                }

            }
            f = f && isUnVisited;
        }

        return  current;
    }

    private PlumbingProduct source(){

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getContent();
            if (plumbingProduct instanceof Source) {
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
}
