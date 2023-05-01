package view.cell;

import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

public abstract class PlumberProductWidget extends CellItemWidget {

    private PlumbingProduct plumbingProduct;
    public PlumberProductWidget(@NotNull PlumbingProduct plumbingProduct){
        this.plumbingProduct = plumbingProduct;
    }

     abstract void fill();


    public boolean isFilled(){
        return plumbingProduct.isFilled();
    }
}
