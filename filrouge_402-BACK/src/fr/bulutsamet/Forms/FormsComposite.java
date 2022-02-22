package fr.bulutsamet.Forms;

import java.util.ArrayList;
import java.util.List;

public class FormsComposite extends Forms {
    //Attribute
    private List<Forms> myAllForms;
    //

    //Method
    @Override
    public double getPerimetre() {
        double totalPerimeterForms = 0;
        for (Forms forms : myAllForms) {
            totalPerimeterForms += forms.getPerimetre();
        }
        return totalPerimeterForms;
    }

    @Override
    public double getAire() {
        double totalAireForms = 0;
        for (Forms forms : myAllForms) {
            totalAireForms += forms.getAire();
        }
        return totalAireForms;
    }

    public void addForm(Forms forms) {
        this.myAllForms.add(forms);
    }

    public void removeForm(Forms forms) {
        this.myAllForms.remove(forms);
    }

    public String toString(){
        return "Total perimeter = " + df.format(getPerimetre()) + " cm, Total Aire = " + df.format(getAire()) + " cm2";
    }
    //

    //Constructor
    public FormsComposite() {
        this.myAllForms = new ArrayList<>();
    }
    //
}
