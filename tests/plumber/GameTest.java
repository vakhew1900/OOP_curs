package plumber;

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
        Assertions.assertEquals(Game.WIN, game.status());
    }

    @Test
    public void finish_LoseTest(){
        Game game = new Game();

        Assertions.assertEquals(Game.RUNNING, game.status());
        game.source().water().fireFlowAction();
        Assertions.assertEquals(Game.LOSE, game.status());
    }
}