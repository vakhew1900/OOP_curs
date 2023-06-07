package model.plumber_product_end;

import model.Direction;
import model.material.Material;

public abstract class AbstractPlumberProductEnd implements Cloneable{

    /**
     * Направление конца трубы
     */
    private Direction direction;

    /** Конструктор
     *
     * @param direction - направление трубы
     */
    public AbstractPlumberProductEnd(Direction direction) {

        this.direction = direction;
    }


    public Direction direction() {
        return direction;
    }

    /** Повернуть конец трубы
     *
     * @return - труба, что повернута на 90 градусов по часовой стрелке относительно исходной
     */
    public AbstractPlumberProductEnd rotate() {

        AbstractPlumberProductEnd other = null;
        try {
            other = (AbstractPlumberProductEnd) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        other.direction = direction.clockwise();
        return  other;
    }

    /** Получить трубу в противоположном направлении
     *
     * @return труба - что повернуто на 180 градусов относительно текущей
     */
    public AbstractPlumberProductEnd opposite() {
        AbstractPlumberProductEnd other = null;
        try {
            other = (AbstractPlumberProductEnd) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        other.direction = other.direction.opposite();
        return other;
    }


    public abstract boolean isCanConnected(AbstractPlumberProductEnd abstractPlumberProductEnd);


    @Override
    public String toString() {
        return direction.toString();
    }

}
