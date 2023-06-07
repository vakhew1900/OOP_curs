package model.plumber_product_end;

import model.Direction;
import model.plumber_product.PlumbingProduct;

public class SimplePlumberProductEnd extends AbstractPlumberProductEnd{


    public SimplePlumberProductEnd(Direction direction) {
        super(direction);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof SimplePlumberProductEnd) {
            SimplePlumberProductEnd otherEnd = (SimplePlumberProductEnd) other;

            return otherEnd.direction().equals(this.direction());
        }

        return false;
    }

    public  SimplePlumberProductEnd rotate(){
        return (SimplePlumberProductEnd) super.rotate();
    }

    public SimplePlumberProductEnd opposite(){
        return (SimplePlumberProductEnd) super.opposite();
    }

    @Override
    public boolean isCanConnected(AbstractPlumberProductEnd abstractPlumberProductEnd) {
        return this.equals(abstractPlumberProductEnd.opposite());
    }

    @Override
    public int hashCode() {
        int hashCode = direction().hashCode();
        return hashCode;
    }

    @Override
    public SimplePlumberProductEnd clone() {
        return new SimplePlumberProductEnd(direction());
    }

}
