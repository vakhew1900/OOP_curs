package model.plumber_product_end;

import model.Direction;
import model.material.Material;

public abstract class PlumberProductEnd implements Cloneable {

    private Direction direction;
    private int diameter;

    private Material material;

    public PlumberProductEnd(Direction direction, int diameter, Material material) {
        if (diameter <= 0) {
            throw new IllegalArgumentException("Illegal diameter");
        }

        this.direction = direction;
        this.material = material;
        this.diameter = diameter;
    }

    public Direction direction() {
        return direction;
    }

    public int diameter() {
        return diameter;
    }

    public Material material() {
        return material;
    }

    public void rotate() {
        direction = direction.clockwise();
    }

    public PlumberProductEnd oposite() {
        PlumberProductEnd other = null;
        try {
            other = (PlumberProductEnd) this.clone();
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

            return otherEnd.direction().equals(this.direction) && this.diameter == otherEnd.diameter
                    && otherEnd.material.equals(this.material);
        }

        return false;
    }


}
