package fr.bulutsamet.FilRougeBack402.Forms3D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DDto;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import fr.bulutsamet.FilRougeBack402.SceneConcept.Scene3dConceptDto;

public class Forms3DDto {
    // Attribute
    public String type;
    public String name;
    public double largeur;
    public double longueur;
    public double base;
    public double rayon;
    public double depths;

    // Method
    public static Forms2D Forms3DSendByUser2Form2D(Forms3DDto dto) {
        return getForms2D(dto.type, dto.name, dto.largeur, dto.longueur, dto.base, dto.rayon);
    }

    public static Forms2D getForms2D(String type, String name, double largeur, double longueur, double base, double rayon) {
        Forms2DDto forms2d = new Forms2DDto();
        forms2d.setType(type);
        forms2d.setName(name);
        forms2d.setLargeur(largeur);
        forms2d.setLongueur(longueur);
        forms2d.setBase(base);
        forms2d.setRayon(rayon);
        return forms2d.toForm2D();
    }

    public static Forms3D Forms3DSendByUser2Forms3D(Forms2D dto, double depths) {
        return new Forms3D(dto, depths);
    }
}
