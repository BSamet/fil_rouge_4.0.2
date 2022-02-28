package fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DService;

import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DService.Forms2D;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Forms3D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double depths;

    @OneToMany
    private Collection<Forms2D> forms2d;

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
