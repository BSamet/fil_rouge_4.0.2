package fr.bulutsamet.FilRougeBack402.Forms2D.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("circle")
public class Circle extends Forms2D {

    //Attribute
    @Column(name = "rayon")
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

    @JsonIgnore
    public double getDiametre() {
        return this.rayon *2;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "id='" + id + '\'' +
                ", rayon=" + rayon +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
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

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) { this.rayon = rayon; }
    //

    //Constructor
    public Circle() {
        this.type = "Circle";
    }

    public Circle(double rayon, String name) {
        this.rayon = rayon;
        this.name = name;
        this.type = "Circle";
    }
    //
}
