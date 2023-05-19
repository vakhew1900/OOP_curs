package model.plumber_product;

import model.Cell;
import model.Direction;
import model.Water;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;

import java.util.HashSet;
import java.util.Set;

public class Pipe extends PlumbingProduct {

    public Pipe(Set<AbstractPlumberProductEnd> ends, Cell cell) {

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

        Set<AbstractPlumberProductEnd> newEnds = new HashSet<>();
        for(AbstractPlumberProductEnd end : getEnds()){
           AbstractPlumberProductEnd newEnd =  end.rotate();
           newEnds.add(newEnd);
        }
         setEnds(newEnds);
    }

    @Override
    public void fill(Water water) {
        super.fill(water);
    }

}
