package fr.bulutsamet.FilRougeBack402.Services.Forms3DService;

import fr.bulutsamet.FilRougeBack402.Services.Forms2DService.Forms2D;
import fr.bulutsamet.FilRougeBack402.Services.Forms2DService.Forms2DDao;
import fr.bulutsamet.FilRougeBack402.Services.UnkownFormsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Forms3DController {

    private final Forms3DDao forms3DDao;
    private final Forms2DDao forms2DDao;

    public Forms3DController(Forms3DDao forms3DDao, Forms2DDao forms2DDao) {
        this.forms3DDao = forms3DDao;
        this.forms2DDao = forms2DDao;
    }

    @RequestMapping("/Forms3D")
    public List<Forms3D> findAllForms3D() {
        return forms3DDao.findAll();
    }

    @GetMapping("/Forms3D/{id}")
    public Forms3D listOneForms3D(@PathVariable int id) throws UnkownFormsException {
        Forms3D forms3d = forms3DDao.findById(id);
        if (forms3d == null) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");
        return forms3d;
    }


    @PostMapping("/Forms3D")
    public ResponseEntity<Void> addForms3D(@RequestBody Forms3D forms3d) {
        int id = forms3d.getForms2dId();
        double depths = forms3d.getDepths();
        Forms3D toSave = new Forms3D();
        toSave.setForms2dId(id);
        toSave.setDepths(depths);
        Forms2D forms2d = forms2DDao.getById(id);
        toSave.setForms2d(forms2d);
        Forms3D formsAdded = forms3DDao.save(toSave);
        if (formsAdded == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(formsAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/Forms3D")
    public void updateForms3D(@RequestBody Forms3D forms3d) {
        forms3DDao.save(forms3d);
    }

    @DeleteMapping("/Forms3D/{id}")
    public void deleteForms3D(@PathVariable int id) {
        forms3DDao.deleteById(id);
    }
}
