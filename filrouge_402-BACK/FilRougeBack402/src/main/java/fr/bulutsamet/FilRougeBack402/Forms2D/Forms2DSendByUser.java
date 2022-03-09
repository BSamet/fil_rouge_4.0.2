package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Circle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Rectangle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Triangle;

public class Forms2DSendByUser {

    public String type;
    public String name;
    public double largeur;
    public double longueur;
    public double base;
    public double rayon;

    public Forms2D Forms2DSendByUser2Form2D(Forms2DSendByUser dto) {
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

    public Forms2DSendByUser() {}
}
