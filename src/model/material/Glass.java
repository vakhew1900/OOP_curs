package model.material;

import java.awt.*;

public class Glass extends Material{
    @Override
    public Color color() {
        return Color.GRAY;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public String toString() {
        return  "glass";
    }
}
