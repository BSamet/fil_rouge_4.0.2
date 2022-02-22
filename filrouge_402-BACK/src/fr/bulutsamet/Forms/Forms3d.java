package fr.bulutsamet.Forms;

import static fr.bulutsamet.Forms.Forms.df;

public class Forms3d {

    //Attribute
    private Forms forms;
    private double profondeur;
    //

    //Method
    public double getVolumes() {
        if (forms instanceof Triangle) {
            if (this.profondeur != 0) {
                double base = ((Triangle) forms).getBase();
                double longueur = ((Triangle) forms).getLongueur();
                double hauteur = Math.sqrt((longueur * longueur) - (base * base) / 4);
                return (float) 1 / 3 * base * this.profondeur * hauteur;
            } else {
                return 0;
            }
        } else if (forms instanceof Rectangle) {
            if (profondeur != 0) {
                double largeur = ((Rectangle) forms).getLargeur();
                double longueur = ((Rectangle) forms).getLongueur();
                return longueur * largeur * profondeur;
            } else {
                return 0;
            }
        } else if (forms instanceof Circle) {
            double rayon = ((Circle) forms).getRayon();
            return (4 * Math.PI * (rayon * rayon * rayon)) / 3;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Le volume de " + forms.name + " est de " + df.format(getVolumes()) + " cm3";
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public double getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }
    //

    //Constructor
    public Forms3d() {
    }

    public Forms3d(Forms forms) {
        this.forms = forms;
    }
    //
}
