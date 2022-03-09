package fr.bulutsamet.FilRougeBack402.Forms3D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DRepository;
import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
import fr.bulutsamet.FilRougeBack402.Forms3D.Model.Forms3D;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Forms3DController {

    private final Forms3DRepository forms3DRepository;
    private final Forms2DRepository forms2DRepository;

    public Forms3DController(Forms3DRepository forms3DRepository, Forms2DRepository forms2DRepository) {
        this.forms3DRepository = forms3DRepository;
        this.forms2DRepository = forms2DRepository;
    }

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


    @PostMapping("/Forms3D")
    public ResponseEntity<Void> addForms3D(@RequestBody Forms3D forms3d) {
        if (forms3d == null) {
            return ResponseEntity.noContent().build();
        }
        int id = forms3d.getForms2dId();
        double depths = forms3d.getDepths();
        Forms3D toSave = new Forms3D();
        toSave.setForms2dId(id);
        toSave.setDepths(depths);
        Forms2D forms2d = forms2DRepository.getById(id);
        Forms3D formsAdded = forms3DRepository.save(toSave);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(formsAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/Forms3D")
    public void updateForms3D(@RequestBody Forms3D forms3d) {
        forms3DRepository.save(forms3d);
    }

    @DeleteMapping("/Forms3D/{id}")
    public void deleteForms3D(@PathVariable int id) {
        forms3DRepository.deleteById(id);
    }
}
