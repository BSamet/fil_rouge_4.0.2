package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Circle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Rectangle;
import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Triangle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Forms2DController {

    //Attribute
    private final Forms2DRepository forms2DRepository;
    //

    //Constructor
    public Forms2DController(Forms2DRepository forms2DRepository) {
        this.forms2DRepository = forms2DRepository;
    }
    //

    //Method
    @RequestMapping("/Forms2D")
    public List<Forms2D> findAllForms2D() {
        return forms2DRepository.findAll();
    }

    @GetMapping("/Forms2D/{id}")
    public Forms2D listOneForms2D(@PathVariable int id) throws UnkownFormsException {
        Forms2D forms2d = forms2DRepository.findById(id);
        if(forms2d ==null) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");
        return forms2d;
    }

    @PostMapping("/Forms2D")
    public ResponseEntity<String> addForms2D(@RequestBody Forms2DSendByUser forms2dDTO) {
        if (forms2dDTO == null) {
            return ResponseEntity.noContent().build();
        }
        Forms2DSendByUser checkForms = new Forms2DSendByUser();
        Forms2D myForms = checkForms.Forms2DSendByUser2Form2D(forms2dDTO);
        Forms2D formsAdded = forms2DRepository.save(myForms);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(formsAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/Forms2D")
    public void updateForms2D(@RequestBody Forms2D forms2d) {
        forms2DRepository.save(forms2d);
    }

    @DeleteMapping("/Forms2D/{id}")
    public void deleteForms2D(@PathVariable int id) {
        forms2DRepository.deleteById(id);
    }
    //
}
