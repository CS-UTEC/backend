package controller;

import data.entities.Symptom;
import data.entities.UserApp;
import data.models.SymptomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.SymptomService;
import services.UserAppService;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/symptom")
public class SymptomController {

    @Autowired
    private UserAppService appService;

    @Autowired
    private SymptomService symptomService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity<?> questions() {
        HashMap <String, String[]> map = new HashMap();
        map.put("questions", Symptom.getQuestions());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SymptomModel input) {
        UserApp user = appService.findOneByPublicityId(input.getPublicityId());
        return new ResponseEntity<>(symptomService.createAndGetDiagnostic(user, input), HttpStatus.OK);
    }
}
