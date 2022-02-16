package fr.bulutsamet.Forms;

public class Triangle extends Forms{

    private double base;
    private double longueur;
    private double hauteur;

    @Override
    public double getPerimetre() {
        return 2 * this.longueur + this.base;
    }

    @Override
    public double getAire() {
        this.hauteur = Math.sqrt((this.longueur * this.longueur) - (this.base * this.base) / 4);
        return (this.base * this.hauteur) / 2;
    }

    @Override
    public double getVolume() {
        return 0;
    }

    public Triangle(double base, double longueur) {
        this.base = base;
        this.longueur = longueur;
    }
}
