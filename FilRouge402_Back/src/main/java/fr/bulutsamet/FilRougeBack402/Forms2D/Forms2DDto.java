package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Circle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Rectangle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Triangle;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

@Getter @Setter
public class Forms2DDto {
    // Attribute
    private int id;
    private String type;
    private String name;
    private double largeur;
    private double longueur;
    private double base;
    private double rayon;

    // Method
        // Convert dto to entity (POST)
    public static Forms2D toEntity2D(Forms2DDto dto) {
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

        // Convert entity to dto (POST)
    public static Forms2DDto toDto2D(Forms2D forms2D) {
        Forms2DDto f = new Forms2DDto();
        f.setName(forms2D.getName());
        switch (forms2D.getType()) {
            case "Rectangle":
                Rectangle rectangle = (Rectangle) forms2D;
                f.setLargeur(rectangle.getLargeur());
                f.setLongueur(rectangle.getLongueur());
                break;
            case "Triangle":
                Triangle triangle = (Triangle) forms2D;
                f.setBase(triangle.getBase());
                f.setLongueur(triangle.getLongueur());
                break;
            case "Circle":
                Circle circle = (Circle) forms2D;
                f.setRayon(circle.getRayon());
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

        // Check shape for update and set attribute
    public static Forms2D Forms2DSendByUser2UpdateForm2D(Optional<Forms2D> forms2d, Forms2DDto dto) {
        if (Objects.equals(forms2d.get().getType(), "Rectangle")) {
            Rectangle rectangle = (Rectangle) forms2d.get();
            rectangle.setName(dto.getName());
            rectangle.setLargeur(dto.getLargeur());
            rectangle.setLongueur(dto.getLongueur());
            return rectangle;
        } else if(Objects.equals(forms2d.get().getType(), "Triangle")) {
            Triangle triangle = (Triangle) forms2d.get();
            triangle.setName(dto.getName());
            triangle.setBase(dto.getBase());
            triangle.setLongueur(dto.getLongueur());
            return triangle;
        } else if(Objects.equals(forms2d.get().getType(), "Circle")){
            Circle circle = (Circle) forms2d.get();
            circle.setName(dto.getName());
            circle.setRayon(dto.getRayon());
            return circle;
        } else {
            return null;
        }
    }
}
