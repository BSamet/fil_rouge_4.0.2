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

    @ManyToOne
    @JoinColumn(name = "forms_2_d_id")
    private Forms2D forms2d;

    public Forms2D getForms2d() {
        return forms2d;
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

    @Override
    public String toString() {
        return "Forms2D{" +
                "id=" + id +
                ", depths=" + depths +
                '}';
    }
}
