package fr.bulutsamet.FilRougeBack402.SceneConcept;

import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
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
    private final Forms3DRepository forms3DRepository;

    public Scene3dConceptController(Scene3dConceptRepository sceneConceptRepository, Forms3DRepository forms3DRepository) {
        this.sceneConceptRepository = sceneConceptRepository;
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

    @PostMapping("/Forms3DComposite/{id}")
    public Forms3DComposite addFormsInForms3DComposite(@RequestBody Scene3dConceptDto forms3dId, @PathVariable int id) {
        Forms3DComposite myScene = sceneConceptRepository.getById(id);
        Forms3D forms3d = forms3DRepository.findById(forms3dId.value);

        myScene.add3dForm(forms3d);
        myScene = sceneConceptRepository.save(myScene);
        return myScene;
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
