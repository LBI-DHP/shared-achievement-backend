package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.PersonDTO;
import at.lbg.dhp.sharedachievementbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> personDTOs = personService.getPersons();
        return new ResponseEntity<>(personDTOs, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<PersonDTO> getPersonById(String id) {
        PersonDTO personDTO = personService.getPerson(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @GetMapping("/stepCount")
    public ResponseEntity<Integer> getStepCountOfPerson(String id) {
        int stepCount = personService.getStepCountOfPerson(id);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        personService.createPerson(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        personService.updatePerson(personDTO);
        return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePerson(String id) {
        personService.deletePerson(id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
