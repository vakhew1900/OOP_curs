package plumber;

import plumber.events.FlowActionEvent;
import plumber.events.FlowActionListener;
import plumber.plumber_product.Drain;
import plumber.plumber_product.PlumbingProduct;
import plumber.plumber_product.Source;

public class Game implements FlowActionListener {


    public final int RUNNING = 0;
    public final int LOSE = 1;
    public final int WIN = 2;


    private int status = RUNNING;
    private GameField gameField;
    private Plumber plumber;
    private Water water;

    public Game() {

        init();
    }

    public void init() {

        gameField = new GameField(5, 5);
        plumber = new Plumber(gameField);
        plumber.configure();
        this.water = source().water();

        water.addFlowActionListener(this);
        drain().addFlowActionListener(this);

    }


    private PlumbingProduct source() {

        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getContent();
            if (plumbingProduct instanceof Source) {
                return plumbingProduct;
            }

        }

        return null;
    }

    private Drain drain() {
        for (int i = 0; i < gameField.height(); i++) {

            PlumbingProduct plumbingProduct = gameField.cell(i, 0).getContent();
            if (plumbingProduct instanceof Drain) {
                return (Drain) plumbingProduct;
            }

        }

        return null;
    }

    public void flowWater() {
        water.flow();
    }

    public void finish(Object obj) {

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
