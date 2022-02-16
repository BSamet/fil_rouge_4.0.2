package fr.bulutsamet.Forms;

public class Rectangle extends Forms {
    private double largeur;
    private double longueur;

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

    public void modifRectangle(double largeur, double longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
    }

    public Rectangle(double largeur, double longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
    }
}
