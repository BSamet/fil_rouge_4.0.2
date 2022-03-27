package fr.bulutsamet.FilRougeBack402.Forms2D;

import fr.bulutsamet.FilRougeBack402.Forms2D.Model.Forms2D;
import fr.bulutsamet.FilRougeBack402.Config.UnkownFormsException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        Optional<Forms2D> forms2DOptional = forms2DRepository.findById(id);
        if(forms2DOptional.isEmpty()) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");
        return forms2DOptional.get();
    }

    @PostMapping("/Forms2D")
    public Forms2D addForms2D(@RequestBody Forms2DDto forms2dDTO) {
        Forms2D myForms = Forms2DDto.toEntity2D(forms2dDTO);
        return forms2DRepository.save(myForms);
    }

    @PutMapping("/Forms2D")
    public Forms2D updateForms2D(@RequestBody Forms2DDto dto) throws UnkownFormsException {
        int id = dto.getId();
        Optional<Forms2D> toUpdateOptional = forms2DRepository.findById(id);
        if(toUpdateOptional.isEmpty()) throw new UnkownFormsException("La forme avec l'id " + id + " est introuvable.");

        Forms2D myForm = Forms2DDto.Forms2DSendByUser2UpdateForm2D(toUpdateOptional, dto);
        return forms2DRepository.save(myForm);
    }

    @DeleteMapping("/Forms2D/{id}")
    public void deleteForms2D(@PathVariable int id) {
        forms2DRepository.deleteById(id);
    }
}
