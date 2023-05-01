package view.cell;

import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

public abstract class PlumberProductWidget extends CellItemWidget {

    public abstract PlumbingProduct plumberProduct();

     abstract void fill();


    public boolean isFilled(){
        return plumberProduct().isFilled();
    }
}
