package fr.bulutsamet.Forms;

import java.util.List;

public class FormsManager {
    //Attribute
    private List<Forms> myAllForms;
    private double myTotalPerimeter = 0;
    private double myTotalAire = 0;
    //

    //Method
    public double calculatePerimeterForms() {
        for (Forms forms : myAllForms) {
            myTotalPerimeter += forms.getPerimetre();
        }
        return myTotalPerimeter;
    }

    public double calculateAireForms() {
        for (Forms forms : myAllForms) {
            myTotalAire += forms.getAire();
        }
        return myTotalAire;
    }

    public String toString(){
        return "Total perimeter = " + myTotalPerimeter + ", Total Aire = " + myTotalAire;
    }
    //

    //Constructor
    public FormsManager(List<Forms> myAllForms) {
        this.myAllForms = myAllForms;
        this.calculatePerimeterForms();
        this.calculateAireForms();
    }
    //
}
