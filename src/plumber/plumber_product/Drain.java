package plumber.plumber_product;

import org.jetbrains.annotations.NotNull;
import plumber.Cell;
import plumber.Direction;
import plumber.Water;
import plumber.events.FlowActionEvent;
import plumber.events.FlowActionListener;
import plumber.plumber_product.PlumbingProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Drain extends PlumbingProduct {


    /**
     * Конструктор
     * @param ends - множество концов
     * @param cell - клетка, в которой будет расположен наш объект
     */
    private Drain(Set<Direction> ends, Cell cell){
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
    public Drain(Direction end, Cell cell){
        this(Stream.of(end).collect(Collectors.toSet()), cell);
    }

    /**
     * Заполнить слив водой
     * @param water - вода
     */
    @Override
    public void fill(@NotNull Water water) {
        super.fill(water);
        fireFlowAction();
    }


    //------  Работа со слушателями------------------------

    //TODO    !!!
    List<FlowActionListener> FlowActionListeners = new ArrayList<>();

    // присоединяет слушателя
    public void addFlowActionListener(FlowActionListener l) {
        if(FlowActionListeners.contains(l) == false)
            FlowActionListeners.add(l);
    }

    // отсоединяет слушателя
    public void removeFlowActionListener(FlowActionListener l) {
        if (FlowActionListeners.contains(l)) {
            FlowActionListeners.remove(l);
        }
    }

    // оповещает слушателей о событии
    protected void fireFlowAction() {
        for (FlowActionListener FlowActionListener : FlowActionListeners) {
            FlowActionListener.flowStopped(new FlowActionEvent(this));
        }
    }
}
