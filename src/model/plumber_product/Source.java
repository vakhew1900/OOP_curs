package model.plumber_product;

import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import org.jetbrains.annotations.NotNull;
import model.Cell;
import model.Direction;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Source extends PlumbingProduct {

    /**
     * Конструктор
     * @param ends - множество концов
     * @param cell - клетка, в которой будет расположен наш объект
     */
    private Source(Set<AbstractPlumberProductEnd> ends, Cell cell){
        super(ends, cell);

        if(ends.size() != 1){
            throw new IllegalArgumentException("illegal exception");
        }

        if(ends.contains(null)){
            throw new IllegalArgumentException("illegal exception");
        }

    }

    /**
     * Конструктор
     * @param end - конец источника
     * @param cell - клетка, в которой будет расположен наш объект
     */
    public Source(@NotNull  AbstractPlumberProductEnd end, Cell cell){
        this(Stream.of(end).collect(Collectors.toSet()), cell);
    }
}
