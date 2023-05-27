package model.material;

import java.awt.*;

public abstract class Material {

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass())
            return  true;

        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public abstract Color color();
}
