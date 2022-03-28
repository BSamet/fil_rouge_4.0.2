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
    @RequestMapping("/Forms3DComposite")
    public List<Forms3DComposite> findAllListForms3DComposite() {
        return sceneConceptRepository.findAll();
    }

    @GetMapping("/Forms3DComposite/{id}")
    public Forms3DComposite listOneForms3DComposite(@PathVariable int id) throws UnkownFormsException {
        Forms3DComposite forms3DComposite = sceneConceptRepository.getById(id);
        return forms3DComposite;
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

    @PostMapping("/Forms3DComposite/{id}/add")
    public Forms3DComposite addFormsInScene(@RequestBody List<Integer> forms3DId , @PathVariable int id) {
        Forms3DComposite scene3dConcept = sceneConceptRepository.getById(id);
        forms3DId.forEach(formsId -> {
            Optional<Forms3D> forms3D = forms3DRepository.findById(formsId);
            forms3D.get().setSceneId(id);
            forms3DRepository.save(forms3D.get());
            scene3dConcept.add3dForm(forms3D.get());
        });
        return sceneConceptRepository.save(scene3dConcept);
    }

    @PutMapping("/Forms3DComposite/{id}")
    public void updateScene(@RequestBody Forms3DComposite forms3DComposite, @PathVariable int id) {
        Forms3DComposite scene3dConcept = sceneConceptRepository.getById(id);
        scene3dConcept.setName(forms3DComposite.getName());
        sceneConceptRepository.save(scene3dConcept);
    }

    @DeleteMapping("/Forms3DComposite/{id}")
    public void deleteForms3DComposite(@PathVariable int id) {
        sceneConceptRepository.deleteById(id);
    }

    @DeleteMapping("/Forms3DComposite/{id}/delete")
    public Forms3DComposite deleteFormsInScene(@RequestBody List<Integer> forms3DId , @PathVariable int id) {
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
