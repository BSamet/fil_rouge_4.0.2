package fr.bulutsamet.FilRougeBack402.Forms3D;

import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DDto;
import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DRepository;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3DComposite;
import fr.bulutsamet.FilRougeBack402.SceneConcept.Scene3dConceptRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Forms3DController {

    //Attribute
    private final Forms3DRepository forms3DRepository;
    private final Forms2DRepository forms2DRepository;
    private final Scene3dConceptRepository scene3dConceptRepository;
    //

    // Constructor
    public Forms3DController(Forms3DRepository forms3DRepository, Forms2DRepository forms2DRepository, Scene3dConceptRepository scene3dConceptRepository) {
        this.forms3DRepository = forms3DRepository;
        this.forms2DRepository = forms2DRepository;
        this.scene3dConceptRepository = scene3dConceptRepository;
    }
    //

    // Method
    @RequestMapping("/Forms3D")
    public List<Forms3D> findAllForms3D() {
        return forms3DRepository.findAll();
    }

    @GetMapping("/Forms3D/{id}")
    public Forms3D listOneForms3D(@PathVariable int id) throws UnkownFormsException {
        Forms3D forms3d = forms3DRepository.findById(id);
        if (forms3d == null) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");
        return forms3d;
    }

    @GetMapping("/Forms3D/scene/{sceneId}")
    public List<Forms3D> listForms3DByScene(@PathVariable int sceneId) {
        return forms3DRepository.findBySceneId(sceneId);
    }

    @PostMapping("/Forms3D")
    public Forms3D addForms3D(@RequestBody Forms3DDto dto) {
        Forms2D convertedMyDtoTo2d = Forms3DDto.forms3DSentByUser2Form2D(dto);

        double depths = dto.getDepths();
        int sceneId = dto.getSceneId();
        Forms3D convertMyDtoTo3d = Forms3DDto.forms3DSentByUser2Forms3D(convertedMyDtoTo2d, depths, sceneId);

        Forms3D savedForms = forms3DRepository.save(convertMyDtoTo3d);

        if(savedForms.getSceneId() != 0) {
            Forms3DComposite myScene = scene3dConceptRepository.getById(savedForms.getSceneId());
            myScene.add3dForm(savedForms);
            scene3dConceptRepository.save(myScene);
        }

        return savedForms;
    }

    @PutMapping("/Forms3D")
    public Forms3D updateForms3D(@RequestBody Forms3DDto dto) {
        Forms3D toUpdate = forms3DRepository.findById(dto.getId());
        Optional<Forms2D> toUpdateForms2D = Optional.ofNullable(toUpdate.getForms2D());

        Forms2D toSaveForms2D = Forms3DDto.forms3DSentByUser2Form2D(dto);

        Forms2D forms2D = Forms2DDto.Forms2DSendByUser2UpdateForm2D(toUpdateForms2D, Forms2DDto.toDto2D(toSaveForms2D));

        Forms3D toFinalSave = Forms3DDto.forms3DSentByUser2UpdateForms3D(toUpdate, dto, forms2D);

        forms2DRepository.save(forms2D);
        return forms3DRepository.save(toFinalSave);
    }

    @DeleteMapping("/Forms3D/{id}")
    public void deleteForms3D(@PathVariable int id) {
        forms3DRepository.deleteById(id);
    }
    //
}
