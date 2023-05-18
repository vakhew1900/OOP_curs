package model.plumber_product;

import model.Cell;
import model.Direction;
import model.Water;
import model.plumber_product_end.PlumberProductEnd;

import java.util.HashSet;
import java.util.Set;

public class Pipe extends PlumbingProduct {

    public Pipe(Set<PlumberProductEnd> ends, Cell cell) {

        super(ends, cell);

        if (ends.size() != 2){
            cell.clear();
            throw new IllegalArgumentException("illegal argument for pipe");
        }
    }

    public void rotate(){
        if (isFilled()){
            return;
        }

        for(PlumberProductEnd end : getEnds()){
           end.rotate();
        }

    }

    @Override
    public void fill(Water water) {
        super.fill(water);
    }

}
