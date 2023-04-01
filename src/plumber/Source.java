package plumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Source extends PlumbingProduct{
    private Source(Set<Direction> ends, Cell cell){
        super(ends, cell);

        if(ends.size() != 1){
            throw new IllegalArgumentException("illegal exception");
        }

    }

    @Override
    public Direction getSuspendedDirection() {
        return null;
    }

    public Source(Direction end, Cell cell){
        this(Stream.of(end).collect(Collectors.toSet()), cell);
    }
}
