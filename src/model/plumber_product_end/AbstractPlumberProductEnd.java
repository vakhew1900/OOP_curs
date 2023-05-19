package model.plumber_product_end;

import model.Direction;
import model.material.Material;

public abstract class AbstractPlumberProductEnd implements Cloneable{

    private Direction direction;

    public AbstractPlumberProductEnd(Direction direction) {

        this.direction = direction;
    }

    public Direction direction() {
        return direction;
    }


    public AbstractPlumberProductEnd rotate() {

        AbstractPlumberProductEnd other = null;
        try {
            other = (AbstractPlumberProductEnd) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        other.direction = direction.clockwise();
        return  other;
    }

    public AbstractPlumberProductEnd opposite() {
        AbstractPlumberProductEnd other = null;
        try {
            other = (AbstractPlumberProductEnd) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        other.direction = other.direction.opposite();
        return other;
    }





    @Override
    public String toString() {
        return direction.toString();
    }

}
