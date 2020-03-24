package controller;

import data.entities.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.SymptomService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/symptom")
public class SymptomController {

    @Autowired
    private SymptomService symptomService;


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable("id") Long id, @RequestBody Symptom input) {
        symptomService.create(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
