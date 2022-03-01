package fr.bulutsamet.FilRougeBack402.Model.Forms2D;

public class Circle extends Forms{

    //Attribute
    private double rayon;
    //

    //Method
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
        return "le perimètre du cercle est de " + df.format(this.getPerimetre()) + " cm et l'aire est de " + df.format(this.getAire()) + " cm2.";
    }

    public double getDiametre() {
        return this.rayon *2;
    }

    //Getter & Setter
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) { this.rayon = rayon; }
    //

    //Constructor
    public Circle() {}

    public Circle(double rayon, String name) {
        this.rayon = rayon;
        this.name = name;
    }
    //
}
