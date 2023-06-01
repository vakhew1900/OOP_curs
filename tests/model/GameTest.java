package model;

import model.events.GameFinishedActionEvent;
import model.events.GameFinishedActionListener;
import model.events.PlumberProductFilledActionEvent;
import model.events.PlumberProductFilledActionListener;
import model.plumber_product.Drain;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.PlumbingProductTest;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {


    // ----------------------- constructorTest ------------------------------


    @Test
    public void constructorTest(){

        Game  game = new Game();
        Assertions.assertEquals(Game.RUNNING, game.status());
    }


    //--------------------- finishGame() ------------------------------------

    @Test
    public void finish_WinTest(){

        Game game = new Game();

        Assertions.assertEquals(Game.RUNNING, game.status());

        game.drain().fill(game.source().water());
        game.drain().water().fireWaterAction();
        Assertions.assertEquals(Game.WIN, game.status());
    }

    @Test
    public void finish_LoseTest(){
        Game game = new Game();

        Assertions.assertEquals(Game.RUNNING, game.status());
        game.source().water().fireWaterAction();
        Assertions.assertEquals(Game.LOSE, game.status());
    }


    @Test
    public void ListenerTest(){
        Game game = new Game();

        MyGameFinishedListener tmp = new MyGameFinishedListener();
        int expectedX = tmp.x + 1;

        game.addGameFinishedActionListener(tmp);
        game.fireGameFinishedAction();
        Assertions.assertEquals(expectedX, tmp.x);

        game.removegameFinishedListener(tmp);
        game.fireGameFinishedAction();
        Assertions.assertEquals(expectedX, tmp.x);
    }

    private class  MyGameFinishedListener implements GameFinishedActionListener {

        private int x;


        @Override
        public void gameFinished(GameFinishedActionEvent event) {
            x++;
        }
    }
}
