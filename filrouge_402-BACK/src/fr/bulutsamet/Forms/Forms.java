package fr.bulutsamet.Forms;

import java.text.DecimalFormat;

public abstract class Forms {
    protected static final DecimalFormat df = new DecimalFormat("0.00");

    public abstract double getPerimetre();

    public abstract double getAire();
}
