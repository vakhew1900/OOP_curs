package plumber;

import org.jetbrains.annotations.NotNull;

public class Water {


    private PlumbingProduct lastFillingPlumbingProduct;
    private Direction waterDirection;

    public PlumbingProduct getLastFillingPlumbingProduct(){
        return lastFillingPlumbingProduct;
    }

    public void flow(){

    }

     void nextPlumbingProduct(@NotNull PlumbingProduct plumbingProduct){

        if(plumbingProduct.isFilled() == false) {
            plumbingProduct.fill(this);
        }
        this.lastFillingPlumbingProduct = plumbingProduct;
    }

    private void nextConnection(@NotNull Direction direction){

        if(getLastFillingPlumbingProduct() == null)
            return;

        PlumbingProduct neighbor =  getLastFillingPlumbingProduct().neighbor(direction);

        if(neighbor!= null){

            boolean flag = getLastFillingPlumbingProduct().isCanFilled(neighbor);

            if(flag){
                nextPlumbingProduct(neighbor);
            }
        }
    }

    private void stop(){

    }

    private void timer(){

    }
}
