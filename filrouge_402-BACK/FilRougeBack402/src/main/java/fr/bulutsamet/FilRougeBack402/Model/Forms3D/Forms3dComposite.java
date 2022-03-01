package fr.bulutsamet.FilRougeBack402.Model.Forms3D;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Forms3dComposite {

    //Attribute
    private final DecimalFormat df = new DecimalFormat("0.00");
    private List<Forms3d> myAll3dForms;
    //

    //Method
    public double getVolumes() {
        double totalVolumes3dForms = 0;
        for (Forms3d forms3d : myAll3dForms) {
            totalVolumes3dForms += forms3d.getVolumes();
        }
        return totalVolumes3dForms;
    }

    public void add3dForm(Forms3d forms3d) {
        this.myAll3dForms.add(forms3d);
    }

    public void remove3dForm(Forms3d forms3d) {
        this.myAll3dForms.remove(forms3d);
    }

    public String toString(){
        return "le volume total des formes est de " + df.format(getVolumes()) + " cm3";
    }
    //

    //Constructor
    public Forms3dComposite() {
        this.myAll3dForms = new ArrayList<>();
    }
    //
}
