package fr.bulutsamet.Forms;

public class Rectangle extends Forms {

    //Attribute
    private double largeur;
    private double longueur;
    //

    //Method
    @Override
    public double getPerimetre() {
        return (this.largeur + this.longueur) * 2;
    }

    @Override
    public double getAire() {
        return this.largeur * this.longueur;
    }

    @Override
    public String toString() {
        return "le perimètre du rectangle est de " + this.getPerimetre() + " cm et l'aire est de " + this.getAire() + " cm2.";
    }
    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    //Constructor
    public Rectangle() {}

    public Rectangle(double largeur, double longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
    }
    //
}
