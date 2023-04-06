package plumber;

import plumber.events.FlowActionEvent;
import plumber.events.FlowActionListener;
import plumber.plumber_product.Drain;
import plumber.plumber_product.PlumbingProduct;
import plumber.plumber_product.Source;

public class Game implements FlowActionListener {


    public final static int RUNNING = 0;
    public final static int LOSE = 1;
    public final static int WIN = 2;


    private int status = RUNNING;
    private GameField gameField;
    private Plumber plumber;
    private Water water;

    private final int SIZE = 8;

    public Game() {

        init();
    }

    private void init() {

        gameField = new GameField(SIZE, SIZE);
        plumber = new Plumber(gameField);
        plumber.configure();
        this.water = source().water();

        water.addFlowActionListener(this);
        drain().addFlowActionListener(this);

    }


    PlumbingProduct source() {

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getContent();
            if (plumbingProduct instanceof Source) {
                return plumbingProduct;
            }

        }

        return null;
    }

    Drain drain() {
        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, gameField.width() - 1).getContent();
            if (plumbingProduct instanceof Drain) {
                return (Drain) plumbingProduct;
            }

        }

        return null;
    }

    public void flowWater() {
        water.start();
    }

    private void finish(Object obj) {

        if (obj instanceof Water) {
            status = LOSE;
        } else {
            status = WIN;
        }
    }

    public int status() {
        return status;
    }

    @Override
    public void flowStopped(FlowActionEvent event) {
        finish(event.getSource());
    }
}
