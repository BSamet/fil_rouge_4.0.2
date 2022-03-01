package fr.bulutsamet.FilRougeBack402.Services.Forms3DService;

import fr.bulutsamet.FilRougeBack402.Services.Forms2DService.Forms2D;

import javax.persistence.*;

@Entity
@Table(name = "Forms3D")
public class Forms3D {
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
    private Forms2D forms2d;

    public Forms2D getForms2d() {
        return forms2d;
    }

    public void setForms2d(Forms2D forms2d) {
        this.forms2d = forms2d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDepths() {
        return depths;
    }

    public void setDepths(double depths) {
        this.depths = depths;
    }

    public int getForms2dId() {
        return forms2dId;
    }

    public void setForms2dId(int forms2dId) {
        this.forms2dId = forms2dId;
    }

    @Override
    public String toString() {
        return "Forms2D{" +
                "id=" + id +
                ", depths=" + depths +
                '}';
    }
}
