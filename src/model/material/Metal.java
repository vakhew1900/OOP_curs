package model.material;

import java.awt.*;

public class Metal extends Material{

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public Color color() {
        return new Color(217, 217, 217);
    }


    @Override
    public String toString() {
        return "metal";
    }


}
