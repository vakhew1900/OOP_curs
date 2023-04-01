package plumber;

import java.util.List;
import java.util.Set;

public class Pipe extends PlumbingProduct{

    public Pipe(Set<Direction> ends, Cell cell) {

        super(ends, cell);

        if (ends.size() > 2){
            throw new IllegalArgumentException("illegal argument for pipe");
        }
    }

    public void rotate(){

    }

    @Override
    public void fill(Water water) {
        super.fill(water);
    }

    @Override
    public Direction getSuspendedDirection() {
        return null;
    }
}
