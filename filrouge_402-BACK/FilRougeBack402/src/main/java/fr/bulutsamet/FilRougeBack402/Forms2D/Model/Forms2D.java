package fr.bulutsamet.FilRougeBack402.Forms2D.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DecimalFormat;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="form_type")
public abstract class Forms2D {
    //Attribute
    @JsonIgnore
    protected static final DecimalFormat df = new DecimalFormat("0.00");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "type")
    protected String type;
    @Column(name = "name")
    protected String name;
    //

    //Method
    @JsonIgnore
    public abstract double getPerimetre();

    @JsonIgnore
    public abstract double getAire();
    //

    //Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract void setName(String name);

    public String getType() {
        return type;
    }

    //
}
