package plumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Source extends PlumbingProduct{
    private Source(ArrayList<Direction> ends, Cell cell){
        super(ends, cell);

        if(ends.size() != 1){
            throw new IllegalArgumentException("illegal exception");
        }

        ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(ends.get(0)));
    }

    public Source(Direction end, Cell cell){
        this(new ArrayList<>(Arrays.asList(end)), cell);
    }
}
