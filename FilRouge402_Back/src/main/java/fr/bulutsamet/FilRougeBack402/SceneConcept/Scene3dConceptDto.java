package fr.bulutsamet.FilRougeBack402.SceneConcept;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;

import static fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DDto.getForms2D;

public class Scene3dConceptDto {
    // Attribute
    public int value;
    private int forms2did;
    public double depths;
    public String type;
    public String name;
    public double largeur;
    public double longueur;
    public double base;
    public double rayon;

    // Method
    public static Forms2D SceneSentByUser2Form2D(Scene3dConceptDto dto) {
        return getForms2D(dto.type, dto.name, dto.largeur, dto.longueur, dto.base, dto.rayon);
    }

    public static Forms3D SceneSendByUser2Forms3D(Forms2D dto, double depths, int sceneId) {
        return new Forms3D(dto, depths, sceneId);
    }
}
