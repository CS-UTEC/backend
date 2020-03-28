package controller;

import data.entities.Symptom;
import data.entities.UserApp;
import data.models.SymptomModel;
import data.models.Diagnostic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.SymptomService;
import services.UserAppService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/symptom")
public class SymptomController {

    @Autowired
    private UserAppService appService;

    @Autowired
    private SymptomService symptomService;

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SymptomModel input) {
        UserApp user = appService.findOrCreate(input.getDocument(), input.getType());
        return new ResponseEntity<>(symptomService.createAndGetDiagnostic(user, input), HttpStatus.OK);
    }
}
