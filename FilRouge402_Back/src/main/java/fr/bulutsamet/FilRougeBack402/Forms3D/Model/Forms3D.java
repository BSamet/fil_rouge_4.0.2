package fr.bulutsamet.FilRougeBack402.Forms3D.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
public class Forms3D {
    //Attribute
    @JsonIgnore
    protected static final DecimalFormat df = new DecimalFormat("0.00");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "depths")
    private double depths;
    @Column(name = "forms2dId")
    private int forms2dId;

    @ManyToOne
    @JoinColumn(name = "forms_2_d_id")
    private Forms2D forms2D;
    //

    //Method
    @JsonIgnore
    public double getVolumes() {
        return forms2D.getAire() * this.depths;
    }

    @Override
    public String toString() {
        return "Forms3D{" +
                "depths=" + depths +
                ", forms2dId=" + forms2dId +
                ", forms2D=" + forms2D +
                '}';
    }
    //

    //Getter & Setter
    public Forms2D getForms2D() {
        return forms2D;
    }

    public double getDepths() {
        return depths;
    }

    public void setDepths(double depths) {
        this.depths = depths;
    }

    public int getId() {
        return id;
    }

    public int getForms2dId() {
        return forms2dId;
    }
    //

    //Constructor
    public Forms3D() {}

    public Forms3D(Forms2D forms2D, double depths, int forms2dId) {
        this.forms2D = forms2D;
        this.depths = depths;
        this.forms2dId = forms2dId;
    }
}
