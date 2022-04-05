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
    @Column(name = "hauteur")
    private double hauteur;
    //

    //Method
    @Override
    public double getPerimetre() {

        double hyp = (hauteur * hauteur) + ((base / 2) * (base / 2));
        double finalHyp = Math.sqrt(hyp);

        double longueur = finalHyp * 2 + base;

        return 2 * longueur + this.base;
    }

    @Override
    public double getAire() {
        return (this.base * this.hauteur) / 2;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", base=" + base +
                ", hauteur=" + hauteur +
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
        if ( base < 0 && base > this.hauteur ) {
            throw new IllegalArgumentException("Erreur lors de la configuration de la base");
        } else {
            this.base = base;
        }
    }

    public double getHauteur() {
        return this.hauteur;
    }

    public void setHauteur(double hauteur) {
        if (hauteur < 0 && hauteur < this.base) {
            throw new IllegalArgumentException("Erreur lors de la configuration de la hauteur");
        } else {
            this.hauteur = hauteur;
        }
    }
    //

    //Constructor
    public Triangle() {
        this.type = "Triangle";
    }

    public Triangle(double base, double hauteur, String name) {
        if (base > hauteur) {
            throw new IllegalArgumentException("La base ne peut être plus long que la hauteur");
        }
        if (hauteur > 0) {
            this.hauteur = hauteur;
        } else {
            throw new IllegalArgumentException("La hauteur ne peut pas valoir 0 ou être négatif");
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
