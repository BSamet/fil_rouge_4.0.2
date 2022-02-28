package fr.bulutsamet.FilRougeBack402.Forms2D.Forms2DService;

import fr.bulutsamet.FilRougeBack402.UnkownFormsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Forms2DController {

    private final Forms2DDao forms2DDao;

    public Forms2DController(Forms2DDao forms2DDao) {
        this.forms2DDao = forms2DDao;
    }

    @RequestMapping("/Forms2D")
    public List<Forms2D> findAllForms2D() {
        return forms2DDao.findAll();
    }

    @GetMapping("/Forms2D/{id}")
    public Forms2D listOneForms2D(@PathVariable int id) throws UnkownFormsException {
        Forms2D forms2d = forms2DDao.findById(id);
        if(forms2d ==null) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");
        return forms2d;
    }

    @PostMapping("/Forms2D")
    public ResponseEntity<Void> addForms2D(@RequestBody Forms2D forms2d) {
        Forms2D formsAdded = forms2DDao.save(forms2d);
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

    @PutMapping("/Forms2D")
    public void updateForms2D(@RequestBody Forms2D forms2d) {
        forms2DDao.save(forms2d);
    }

    @DeleteMapping("/Forms2D/{id}")
    public void deleteForms2D(@PathVariable int id) {
        forms2DDao.deleteById(id);
    }
}
