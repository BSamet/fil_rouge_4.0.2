package fr.bulutsamet.Forms;

public class Rectangle extends Forms {
    private double largeur;
    private double longueur;
    private double hauteur;

    @Override
    public double getPerimetre() {
        return (this.largeur + this.longueur) * 2;
    }

    @Override
    public double getAire() {
        return this.largeur * this.longueur;
    }

    @Override
    public double getVolume() {
       return this.largeur * this.longueur * this.hauteur;
    }

    public Rectangle(double largeur, double longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
    }

    public Rectangle(double largeur, double longueur, double hauteur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
    }
}
