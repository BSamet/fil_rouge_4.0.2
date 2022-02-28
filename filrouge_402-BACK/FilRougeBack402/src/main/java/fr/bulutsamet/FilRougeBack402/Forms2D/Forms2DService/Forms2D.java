package fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DService;

import fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DService.Forms3D;
import io.swagger.annotations.Api;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Forms2D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double longueur;
    private double largeur;
    private double rayon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public String toString() {
        return "Forms2D{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longueur=" + longueur +
                ", largeur=" + largeur +
                ", rayon=" + rayon +
                '}';
    }
}
