package fr.bulutsamet.Forms3d;

import fr.bulutsamet.Forms.Forms;
import java.text.DecimalFormat;

public class Forms3d {
    //Attribute
    private final DecimalFormat df = new DecimalFormat("0.00");
    private Forms forms;
    private double depths;
    //

    //Method
    public double getVolumes() {
        return forms.getAire() * this.depths;
    }

    @Override
    public String toString() {
        return "Le volume de " + forms.getName() + " est de " + df.format(getVolumes()) + " cm3";
    }

    //

    //Getter & Setter
    public Forms getForms() {
        return this.forms;
    }

    public void setForms(Forms forms) {
        forms = this.forms;
    }

    public double getDepths() {
        return depths;
    }

    public void setDepths(double depths) {
        this.depths = depths;
    }

    //

    //Constructor
    public Forms3d(Forms forms, double depths) {
        this.forms = forms;
        this.depths = depths;
    }
}
