package fr.bulutsamet.Forms;

public class Triangle extends Forms{

    //Attribute
    private double base;
    private double longueur;
    //

    //Method
    @Override
    public double getPerimetre() {
        return 2 * this.longueur + this.base;
    }

    @Override
    public double getAire() {
        double hauteur = Math.sqrt((this.longueur * this.longueur) - (this.base * this.base) / 4);
        return (this.base * hauteur) / 2;
    }

    @Override
    public String toString() {
        return "le perimètre du triangle est de " + this.getPerimetre() + " cm et l'aire est de " + this.getAire() + " cm2.";
    }
    //

    //Getter & Setter
    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    //

    //Constructor
    public Triangle() {}

    public Triangle(double base, double longueur) {
        this.base = base;
        this.longueur = longueur;
    }
    //
}
