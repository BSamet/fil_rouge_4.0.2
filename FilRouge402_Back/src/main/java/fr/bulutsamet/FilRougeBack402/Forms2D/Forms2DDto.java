package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Circle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Rectangle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Triangle;

public class Forms2DDto {
    // Attribute
    public String type;
    public String name;
    public double largeur;
    public double longueur;
    public double base;
    public double rayon;

    // Method
    public static Forms2D Forms2DSendByUser2Form2D(Forms2DDto dto) {
        Forms2D f = null;
        switch (dto.type) {
            case "Rectangle":
                f = new Rectangle(dto.largeur, dto.longueur, dto.name);
                break;
            case "Triangle":
                f = new Triangle(dto.base, dto.longueur, dto.name);
                break;
            case "Circle":
                f = new Circle(dto.rayon, dto.name);
                break;
        }
        return f;
    }

    public Forms2D toForm2D() {
        Forms2D f = null;
        switch (this.type) {
            case "Rectangle":
                f = new Rectangle(this.largeur, this.longueur, this.name);
                break;
            case "Triangle":
                f = new Triangle(this.base, this.longueur, this.name);
                break;
            case "Circle":
                f = new Circle(this.rayon, this.name);
                break;
        }
        return f;
    }

    // Getter Setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
}
