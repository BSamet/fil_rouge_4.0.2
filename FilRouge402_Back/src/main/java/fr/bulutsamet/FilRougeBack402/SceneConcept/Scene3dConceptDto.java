package fr.bulutsamet.FilRougeBack402.SceneConcept;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DDto.getForms2D;

@Getter @Setter
public class Scene3dConceptDto {
    // Attribute
    private int value;
    private int forms2did;
    private double depths;
    private String type;
    private String name;
    private double largeur;
    private double longueur;
    private double base;
    private double rayon;
    private List<Integer> forms3DId;

    // Method
    public static Forms2D SceneSentByUser2Form2D(Scene3dConceptDto dto) {
        return getForms2D(dto.type, dto.name, dto.largeur, dto.longueur, dto.base, dto.rayon);
    }

    public static Forms3D SceneSendByUser2Forms3D(Forms2D dto, double depths, int sceneId) {
        return new Forms3D(dto, depths, sceneId);
    }
}
