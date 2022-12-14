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
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Scene3dConceptController {

    // Attribute
    private final Scene3dConceptRepository sceneConceptRepository;
    private final Forms3DRepository forms3DRepository;
    //

    // Constructor
    public Scene3dConceptController(Scene3dConceptRepository sceneConceptRepository, Forms3DRepository forms3DRepository) {
        this.sceneConceptRepository = sceneConceptRepository;
        this.forms3DRepository = forms3DRepository;
    }
    //

    // Method
    @GetMapping("/Forms3DComposite")
    public List<Forms3DComposite> findAllListForms3DComposite() {
        return sceneConceptRepository.findAll();
    }

    @GetMapping("/Forms3DComposite/{id}")
    public Optional<Forms3DComposite> listOneForms3DComposite(@PathVariable int id) throws UnkownFormsException {
        return sceneConceptRepository.findById(id);
    }

    @PostMapping("/Forms3DComposite")
    public ResponseEntity<Object> addNewScene(@RequestBody Forms3DComposite forms3DComposite) {
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
    public Forms3DComposite addFormsInScene(@RequestBody Scene3dConceptDto dto, @PathVariable int id) {
        Forms3DComposite scene3dConcept = null;
        if(id != 0) {
            scene3dConcept = sceneConceptRepository.getById(id);
        } else {
            scene3dConcept = new Forms3DComposite();
            scene3dConcept.setName(dto.getName());
            Forms3DComposite newScene = sceneConceptRepository.save(scene3dConcept);
            scene3dConcept = sceneConceptRepository.getById(newScene.getId());
        }

        Forms3DComposite finalScene3dConcept = scene3dConcept;

        dto.getForms3DId().forEach(formsId -> {
            Optional<Forms3D> forms3D = forms3DRepository.findById(formsId);
            forms3D.get().setSceneId(finalScene3dConcept.getId());
            forms3DRepository.save(forms3D.get());
            finalScene3dConcept.add3dForm(forms3D.get());
        });
        return sceneConceptRepository.save(finalScene3dConcept);
    }

    @PutMapping("/Forms3DComposite/{id}")
    public Forms3DComposite updateScene(@RequestBody Forms3DComposite forms3DComposite, @PathVariable int id) {
        Forms3DComposite scene3dConcept = sceneConceptRepository.getById(id);
        scene3dConcept.setName(forms3DComposite.getName());
        return sceneConceptRepository.save(scene3dConcept);
    }

    @DeleteMapping("/Forms3DComposite/{id}")
    public void deleteForms3DComposite(@PathVariable int id) {
        Forms3DComposite scene3dConcept = sceneConceptRepository.getById(id);
        List<Forms3D> myAllForms = scene3dConcept.getMyAll3dForms();
        for (Forms3D myAllForm : myAllForms) {
            Forms3D forms3D = forms3DRepository.findById(myAllForm.getId());
            forms3D.setSceneId(0);
            forms3DRepository.save(forms3D);
        }
        sceneConceptRepository.deleteById(id);
    }

    @DeleteMapping("/Forms3DComposite/{id}/delete")
    public Forms3DComposite deleteFormsInScene(@RequestBody List<Integer> forms3DId, @PathVariable int id) {
        Forms3DComposite scene3dConcept = sceneConceptRepository.getById(id);
        forms3DId.forEach(formsId -> {
            Optional<Forms3D> forms3D = forms3DRepository.findById(formsId);
            forms3D.get().setSceneId(0);
            forms3DRepository.save(forms3D.get());
            scene3dConcept.remove3dForm(forms3D.get());
        });
        return sceneConceptRepository.save(scene3dConcept);
    }
    //
}
