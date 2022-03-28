package fr.bulutsamet.FilRougeBack402.Forms3D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DDto;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Forms3DDto {
    // Attribute
    private int id;
    private String type;
    private String name;
    private double largeur;
    private double longueur;
    private double base;
    private double rayon;
    private double depths;
    private int sceneId;

    // Method
    public static Forms2D forms3DSentByUser2Form2D(Forms3DDto dto) {
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

    public static Forms3D forms3DSentByUser2Forms3D(Forms2D dto, double depths, int sceneId) {
        return new Forms3D(dto, depths, sceneId);
    }

    public static Forms3D forms3DSentByUser2UpdateForms3D(Forms3D forms3D, Forms3DDto dto, Forms2D forms2D) {
        forms3D.setDepths(dto.getDepths());
        forms3D.setForms2D(forms2D);
        forms3D.setSceneId(dto.sceneId);
        return forms3D;
    }
}
