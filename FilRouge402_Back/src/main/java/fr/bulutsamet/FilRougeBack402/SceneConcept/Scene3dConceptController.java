package fr.bulutsamet.FilRougeBack402.SceneConcept;

import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DRepository;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Forms3DRepository;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3DComposite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Scene3dConceptController {

    private final Scene3dConceptRepository sceneConceptRepository;
    private final Forms2DRepository forms2DRepository;
    private final Forms3DRepository forms3DRepository;

    public Scene3dConceptController(Scene3dConceptRepository sceneConceptRepository, Forms2DRepository forms2DRepository, Forms3DRepository forms3DRepository) {
        this.sceneConceptRepository = sceneConceptRepository;
        this.forms2DRepository = forms2DRepository;
        this.forms3DRepository = forms3DRepository;
    }

    @RequestMapping("/Forms3DComposite")
    public List<Forms3DComposite> findAllListForms3DComposite() {
        return sceneConceptRepository.findAll();
    }

    @GetMapping("/Forms3DComposite/{id}")
    public Forms3DComposite listOneForms3DComposite(@PathVariable int id) throws UnkownFormsException {
        Forms3DComposite forms3DComposite = sceneConceptRepository.findById(id);
        if (forms3DComposite == null) throw new UnkownFormsException("La liste de forme avec l'id " + id + " est introuvable.");
        return forms3DComposite;
    }

    @PostMapping("/Forms3DComposite")
    public ResponseEntity<Object> addForms3DComposite(@RequestBody Forms3DComposite forms3DComposite) {
        if (forms3DComposite == null) {
            return ResponseEntity.noContent().build();
        }
        Forms3DComposite sceneCreated = sceneConceptRepository.save(forms3DComposite);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sceneCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/Forms3DComposite/{id}")
//    public Forms3DComposite addFormsInForms3DComposite(@RequestBody Scene3dConceptDto forms3dId, @PathVariable int id) {
//        Forms3DComposite myScene = sceneConceptRepository.getById(id);
//        Forms3D forms3d = forms3DRepository.findById(forms3dId.value);
//
//        myScene.add3dForm(forms3d);
//        myScene = sceneConceptRepository.save(myScene);
//        return myScene;
//    }

    @PostMapping("/Forms3DComposite/test")
    public void ThisIsMyTest(@RequestBody Scene3dConceptDto thisIsMyTest) {
        // Take the data and convert to shape 2D
        Scene3dConceptDto toSave = new Scene3dConceptDto();
        Forms2D convertedMyDtoTo2d = toSave.SceneSentByUser2Form2D(thisIsMyTest);

        double depths = thisIsMyTest.depths;
        Forms3D convertMyDtoTo3d = toSave.SceneSendByUser2Forms3D(convertedMyDtoTo2d, depths);
        Forms3D saveMyForms3d = forms3DRepository.save(convertMyDtoTo3d);

        System.out.println("3D Shape:");
        System.out.println("================");
        System.out.println(saveMyForms3d);
        System.out.println("================");
    }

    @PutMapping("/Forms3DComposite")
    public void updateForms3DComposite(@RequestBody Forms3DComposite forms3DComposite) {
        sceneConceptRepository.save(forms3DComposite);
    }

    @DeleteMapping("/Forms3DComposite/{id}")
    public void deleteForms3DComposite(@PathVariable int id) {
        sceneConceptRepository.deleteById(id);
    }
}
