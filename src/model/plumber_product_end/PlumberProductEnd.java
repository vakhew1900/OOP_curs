package model.plumber_product_end;

import model.Direction;
import model.material.Material;

public class PlumberProductEnd extends AbstractPlumberProductEnd {


    private int diameter;

    private Material material;

    public static final int BIG_DIAMETER = 80;
    public static final int SMALL_DIAMETER = 50;


    public PlumberProductEnd(Direction direction, int diameter, Material material) {
        super(direction);
        if (diameter <= 0) {
            throw new IllegalArgumentException("Illegal diameter");
        }

        this.material = material;
        this.diameter = diameter;
    }

    public PlumberProductEnd(PlumberProductEnd plumberProductEnd){
        this(plumberProductEnd.direction(), plumberProductEnd.diameter(),plumberProductEnd.material);
    }

    public int diameter() {
        return diameter;
    }

    public Material material() {
        return material;
    }

    @Override
    public PlumberProductEnd rotate() {
        return (PlumberProductEnd) super.rotate();
    }

    @Override
    public PlumberProductEnd opposite() {
        return (PlumberProductEnd) super.opposite();
    }

    @Override

    public boolean equals(Object other) {
        if (other instanceof PlumberProductEnd) {
            PlumberProductEnd otherEnd = (PlumberProductEnd) other;

            return otherEnd.direction().equals(this.direction()) && this.diameter == otherEnd.diameter
                    && otherEnd.material.equals(this.material);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return material.hashCode() * 1000 * 1000 + diameter * 1000 + direction().hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + " " + material.toString();
    }
}
