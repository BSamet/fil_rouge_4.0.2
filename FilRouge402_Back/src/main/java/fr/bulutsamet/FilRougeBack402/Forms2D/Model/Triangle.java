package fr.bulutsamet.FilRougeBack402.Forms2D.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("triangle")
public class Triangle extends Forms2D {

    //Attribute
    @Column(name = "base")
    private double base;
    @Column(name = "longueur")
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
        return "Triangle{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", base=" + base +
                ", longueur=" + longueur +
                '}';
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

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        if ( base < 0 && base > this.longueur ) {
            throw new IllegalArgumentException("Erreur lors de la configuration de la base");
        } else {
            this.base = base;
        }
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        if (longueur < 0 && longueur < this.base) {
            throw new IllegalArgumentException("Erreur lors de la configuration de la longueur");
        } else {
            this.longueur = longueur;
        }
    }
    //

    //Constructor
    public Triangle() {
        this.type = "Triangle";
    }

    public Triangle(double base, double longueur, String name) {
        if (base > longueur) {
            throw new IllegalArgumentException("La base ne peut être plus long que la longueur");
        }
        if (longueur > 0) {
            this.longueur = longueur;
        } else {
            throw new IllegalArgumentException("La longueur ne peut pas valoir 0 ou être négatif");
        }
        if ( base > 0){
            this.base = base;
        } else {
            throw new IllegalArgumentException("La base ne peut pas valoir 0 ou être négatif");
        }
        this.name = name;
        this.type = "Triangle";
    }
    //
}
