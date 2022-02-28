package fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DService;

import fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DService.Forms3D;

import javax.persistence.*;

@Entity
@Table(name = "Forms2D")
public class Forms2D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "longueur")
    private double longueur;
    @Column(name = "largeur")
    private double largeur;
    @Column(name = "rayon")
    private double rayon;

    @ManyToOne
    @JoinColumn(name = "forms3d_id")
    private Forms3D forms3d;

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
