package plumber;

import java.util.List;

public class Pipe extends PlumbingProduct{

    public Pipe(List<Direction> ends, Cell cell) {

        super(ends, cell);

        if (ends.size() > 2){
            throw new IllegalArgumentException("illegal argument for pipe");
        }
    }

    public void rotate(){

    }

    @Override
    public void fill() {
        super.fill();
    }
}
