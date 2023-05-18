package model.plumber_product_end;

import model.Direction;
import model.material.Material;

public class SmallPlumberProductEnd extends PlumberProductEnd{

    public SmallPlumberProductEnd(Direction direction, Material material) {
        super(direction, 50, material);
    }
}
