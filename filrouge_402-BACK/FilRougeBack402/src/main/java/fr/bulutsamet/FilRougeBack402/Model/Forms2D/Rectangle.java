package fr.bulutsamet.FilRougeBack402.Model.Forms2D;

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
        return "le perimètre du rectangle est de " + df.format(this.getPerimetre()) + " cm et l'aire est de " + df.format(this.getAire()) + " cm2.";
    }
    //

    //Getter & Setter
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
         this.name = name;
    }

    public double getLargeur() {
        return this.largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getLongueur() {
        return this.longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    //

    //Constructor
    public Rectangle() {}

    public Rectangle(double largeur, double longueur, String name) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.name = name;
    }
    //
}
