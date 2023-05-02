package view.cell;

import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

public abstract class PlumberProductWidget extends CellItemWidget {

    public abstract PlumbingProduct plumberProduct();

    public PlumberProductWidget(){
        super();
    }

    public boolean isFilled(){
        return plumberProduct().isFilled();
    }
}
