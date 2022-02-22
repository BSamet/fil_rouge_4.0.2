package fr.bulutsamet.Forms;

import java.text.DecimalFormat;

public abstract class Forms {
    //Attribute
    protected String name;
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    //

    //Method
    public abstract double getPerimetre();

    public abstract double getAire();
    //
}
