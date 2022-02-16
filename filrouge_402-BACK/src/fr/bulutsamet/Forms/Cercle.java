package fr.bulutsamet.Forms;

public class Cercle extends Forms{

    private double rayon;

    @Override
    public double getPerimetre() {
        return getDiametre() * Math.PI;
    }

    @Override
    public double getAire() {
        return (this.rayon * this.rayon) * Math.PI;
    }

    @Override
    public String toString() {
        return "le perimètre du cercle est de " + this.getPerimetre() + " cm et l'aire est de " + this.getAire() + " cm2.";
    }

    public double getDiametre() {
        return this.rayon *2;
    }

    public void modifCercle(double rayon) {
        this.rayon = rayon;
    }

    public Cercle(double rayon) {
        this.rayon = rayon;
    }
}
