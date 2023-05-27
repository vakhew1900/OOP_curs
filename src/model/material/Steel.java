package model.material;

import java.awt.*;

public class Steel extends Metal{

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
    public String toString() {
        return "steel";
    }

    @Override
    public Color color() {
        return Color.darkGray;
    }
}
