package fr.bulutsamet.FilRougeBack402.Forms3D.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Forms3DComposite {

    //Attribute
    @JsonIgnore
    protected static final DecimalFormat df = new DecimalFormat("0.00");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "my_alla3d_forms")
    private List<Forms3D> myAll3dForms;

    //

    //Method
    @JsonIgnore
    public double getVolumes() {
        double totalVolumes3dForms = 0;
        for (Forms3D forms3d : myAll3dForms) {
            totalVolumes3dForms += forms3d.getVolumes();
        }
        return totalVolumes3dForms;
    }

    public void add3dForm(Forms3D forms3d) {
        this.myAll3dForms.add(forms3d);
    }

    public void remove3dForm(Forms3D forms3d) {
        this.myAll3dForms.remove(forms3d);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Forms3D> getMyAll3dForms() {
        return myAll3dForms;
    }

    @Override
    public String toString() {
        return "Forms3DComposite{" +
                "myAll3dForms=" + myAll3dForms +
                '}';
    }
    //

    //Constructor
    public Forms3DComposite() {
        this.myAll3dForms = new ArrayList<>();
    }
    //
}
