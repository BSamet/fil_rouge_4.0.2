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
    public double getVolume() {
        return 0;
    }

    public double getDiametre() {
        return this.rayon *2;
    }

    public Cercle(double rayon) {
        this.rayon = rayon;
    }
}
