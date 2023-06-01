package model.plumber_product;

import model.events.PlumberProductFilledActionEvent;
import model.events.PlumberProductFilledActionListener;
import model.events.WaterStoppedActionListener;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import org.jetbrains.annotations.NotNull;
import model.Cell;
import model.Direction;
import model.Water;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PlumbingProduct {

    /**
     * Множество концов
     */
    private Set<AbstractPlumberProductEnd> ends = new HashSet<>();

    /**
     * Клетка, в  которой расположен объет
     */
    private Cell cell;

    // ------------------ Конструктор ----------------------------------------------

    /**
     * геттер для ends
     *
     * @return ends - множество концов
     */
    public Set<AbstractPlumberProductEnd> getEnds() {
        return ends;
    }

    public List<AbstractPlumberProductEnd> getEndsList(){
        return new ArrayList<>(ends);
    }

    /**
     *  сеттер для ends
     * @param ends - множество концов
     */
    protected void setEnds(@NotNull Set<AbstractPlumberProductEnd> ends) {
        this.ends = ends;
    }

    /**
     * Конструктор
     * @param ends - множество концов
     * @param cell - клетка, в которой будет расположен наш объект
     */
    public PlumbingProduct(@NotNull Set<AbstractPlumberProductEnd> ends, Cell cell) {


        if (cell == null || ends == null || ends.size() == 0) {
            throw new IllegalArgumentException("Illegal argument for PlumbingProduct");
        }

        this.ends = new HashSet<AbstractPlumberProductEnd>(ends);
        this.cell = cell;
        cell.fill(this);
    }


    // --------------------- взаимодействие с водой ----------------------------------

    /** Вода, заполняющая сантехническое изделие*/
    private Water water = null;

    /**
     * Заполняет сантехническое изделие водой
     * @param water - вода
     */
    public void fill(@NotNull Water water) {


        if (isFilled() == false) {
            this.water = water;
            if (water.getLastFillingPlumbingProduct() == null || water.getLastFillingPlumbingProduct().equals(this) == false) {
                water.nextPlumbingProduct(this);
            }
        }

        firePlumberProductAction();
    }

    /**
     * Проверяет, заполнена ли сантехническое изделие водой
     * @return true - если сантехническое изделие заполнено водой, иначе false
     */
    public boolean isFilled() {
        return water != null;
    }

    /**
     * геттер для water
     * @return
     */
    public Water water() {
        return water;
    }

    // --------------------------- работа с  концами ------------------------------------

    /**
     * Проверяет есть ли у сантехнического изделия есть заданное направление конца
     * @param plumberProductEnd - заданное направление
     * @return true - если у сантехнического изделия есть заданное направление конца, иначе false
     */
    public boolean hasEnd(AbstractPlumberProductEnd plumberProductEnd) {
        boolean dbg = ends.contains(plumberProductEnd);


        for(AbstractPlumberProductEnd e : ends ){
            System.out.println(e.hashCode() + " " + e.toString());
        }

        return ends.contains(plumberProductEnd);
    }

    public boolean hasEnd(Direction direction){
        return getEnd(direction) != null;
    }
    public AbstractPlumberProductEnd getEnd(Direction direction){

        for (AbstractPlumberProductEnd e : ends){
            if (e.direction().equals(direction)){
                return e;
            }
        }

        return null;
    }

    public PlumbingProduct neighbor(Direction direction) {

        Cell neighborCell = cell.neighbor(direction);

        if (neighborCell != null) {
            return neighborCell.getPlumbingProduct();
        }

        return null;
    }

    // ----------------------------- взаимодействие с трубой -----------------------------

    /**
     * проверяет возможно заполнить другого сантехническое изделие из заданногого.
     * @param other - другая труба
     * @return  true - если возможно заполнить другого сантехническое изделие из заданногого - false
     */
    public boolean isCanFilled(PlumbingProduct other) {
        return other != null && other.isFilled() == false && isConnected(other);
    }

    /**
     * Проверяет соедино ли данное сантехническое изделие с другим
     * @param other - другое сантехническое изделие
     * @return true - если  соедино ли данное сантехническое изделие с другим
     */
    public boolean isConnected(@NotNull PlumbingProduct other) {

        AbstractPlumberProductEnd plumberProductEnd = null;

        for (AbstractPlumberProductEnd end : ends) {

            Direction elem  = end.direction();

            if (neighbor(elem) != null && neighbor(elem).equals(other)) {
                plumberProductEnd = end;
            }
        }

        return plumberProductEnd!= null && other.hasEnd(plumberProductEnd.opposite());
    }



    //------------------------ Системные ----------------------------



    @Override
    public String toString() {

        String res = this.getClass().toString() + ":" + "Cell :" + this.cell.toString() + " Directions :";

        for (AbstractPlumberProductEnd plumberProductEnd : getEnds()) {
            res += plumberProductEnd.toString() + " ";
        }
        return res;
    }

    //TODO    !!!
    List<PlumberProductFilledActionListener> plumberProductFilledActionListeners = new ArrayList<>();

    // присоединяет слушателя
    public void addPlumberProductFilledActionListener(PlumberProductFilledActionListener l) {

        if (plumberProductFilledActionListeners.contains(l) == false)
            plumberProductFilledActionListeners.add(l);
    }

    // отсоединяет слушателя
    public void removeFlowActionListener(PlumberProductFilledActionListener l) {
        if (plumberProductFilledActionListeners.contains(l)) {
            plumberProductFilledActionListeners.remove(l);
        }
    }

    // оповещает слушателей о событии
    protected void firePlumberProductAction() {
        for (PlumberProductFilledActionListener plumberProductFilledActionListener : plumberProductFilledActionListeners) {
            plumberProductFilledActionListener.plumberProductFilled(new PlumberProductFilledActionEvent(this));
        }
    }
}
