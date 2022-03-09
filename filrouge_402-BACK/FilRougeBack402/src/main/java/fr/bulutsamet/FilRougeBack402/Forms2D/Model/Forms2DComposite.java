package fr.bulutsamet.FilRougeBack402.Forms2D.Model;

import java.util.ArrayList;
import java.util.List;

public class Forms2DComposite extends Forms2D {

    //Attribute
    private List<Forms2D> myAllForms;
    //

    //Method
    @Override
    public double getPerimetre() {
        double totalPerimeterForms = 0;
        for (Forms2D forms2D : myAllForms) {
            totalPerimeterForms += forms2D.getPerimetre();
        }
        return totalPerimeterForms;
    }

    @Override
    public double getAire() {
        double totalAireForms = 0;
        for (Forms2D forms2D : myAllForms) {
            totalAireForms += forms2D.getAire();
        }
        return totalAireForms;
    }

    public void addForm(Forms2D forms2D) {
        this.myAllForms.add(forms2D);
    }

    public void removeForm(Forms2D forms2D) {
        this.myAllForms.remove(forms2D);
    }

    public String toString(){
        return "Total perimeter = " + df.format(getPerimetre()) + " cm, Total Aire = " + df.format(getAire()) + " cm2";
    }
    //

    //Getter & Setter
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }
    //

    //Constructor
    public Forms2DComposite() {
        this.myAllForms = new ArrayList<>();
    }
    //
}
