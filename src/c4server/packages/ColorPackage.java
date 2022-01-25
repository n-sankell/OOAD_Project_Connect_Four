package c4server.packages;

import java.awt.*;
import java.io.Serializable;

public class ColorPackage implements Serializable {

    private final Color color;

    public ColorPackage(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}