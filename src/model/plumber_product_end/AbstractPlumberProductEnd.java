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


    public void rotate() {
        direction = direction.clockwise();
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

    public boolean equals(Object other) {
        if (other instanceof PlumberProductEnd) {
            PlumberProductEnd otherEnd = (PlumberProductEnd) other;

            return otherEnd.direction().equals(this.direction)
        }

        return false;
    }

    @Override
    public int hashCode() {
        return direction.hashCode();
    }

    @Override
    public String toString() {
        return direction.toString() + " " + direction;
    }

}
