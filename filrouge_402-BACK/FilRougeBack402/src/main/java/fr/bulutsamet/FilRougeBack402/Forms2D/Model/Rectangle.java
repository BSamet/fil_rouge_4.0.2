package fr.bulutsamet.FilRougeBack402.Forms2D.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("rectangle")
public class Rectangle extends Forms2D {

    //Attribute
    @Column(name = "largeur")
    private double largeur;
    @Column(name = "longueur")
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
    public Rectangle() {
        this.type = "Rectangle";
    }

    public Rectangle(double largeur, double longueur, String name) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.name = name;
        this.type = "Rectangle";
    }
    //
}
