package model.material;

import java.awt.*;

public abstract class Material {


    /** Проверить, являются ли объекты эквивалентными
     *
     * @param obj - другой объект
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass())
            return  true;

        return false;
    }

    /** Вернуть хэш-код объекта
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /** Вернуть цвет материала
     *
     * @return цвет материала
     */
    public abstract Color color();
}
