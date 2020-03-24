package controller;

import services.UbicationService;
import services.UserAppService;
import data.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ubication")
public class UbicationController {

    @Autowired
    private UbicationService ubicationService;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> find() {
        return new ResponseEntity<>(ubicationService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable("id") Long id, @RequestBody Ubication input) {
        UserApp user = appService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        input.setUser(user);
        ubicationService.create(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
