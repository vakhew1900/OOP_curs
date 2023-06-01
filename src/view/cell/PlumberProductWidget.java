package view.cell;

import model.events.PlumberProductFilledActionEvent;
import model.events.PlumberProductFilledActionListener;
import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class PlumberProductWidget extends CellItemWidget implements PlumberProductFilledActionListener {

    private PlumbingProduct plumbingProduct;

    protected Color waterColor = new Color(5, 225, 225);

    public  PlumbingProduct plumberProduct(){
        return plumbingProduct;
    }

    public PlumberProductWidget(@NotNull PlumbingProduct plumbingProduct){
        super();
        this.plumbingProduct = plumbingProduct;
        plumbingProduct.addPlumberProductFilledActionListener(this);
    }

    public boolean isFilled(){
        return plumberProduct().isFilled();
    }

    @Override
    public void plumberProductFilled(PlumberProductFilledActionEvent plumberProductFilledActionEvent) {
        repaint();
    }

    @Override
    protected String getPath() {
        return "image/";
    }
}
