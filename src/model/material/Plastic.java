package model.material;

import java.awt.*;

public class Plastic extends Material{

    @Override
    public int hashCode() {
        return 3;
    }

    @Override
    public Color color() {
        return Color.PINK;
    }

    @Override
    public String toString() {
        return "plastic";
    }
}
