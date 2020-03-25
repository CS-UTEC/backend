package controller;

import services.UbicationService;
import services.UserAppService;
import data.entities.*;
import data.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ubication")
public class UbicationController {

    @Autowired
    private UbicationService service;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> find() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable String id, @RequestBody UbicationModel input) {
        UserApp user = appService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.create(input, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/circle", method = RequestMethod.POST)
    public ResponseEntity<?> findByCircleArea(@RequestBody CircleArea input) {
        List<Ubication> circleArea = service.findByCircle(input.getCenter(), input.getRadius());
        return new ResponseEntity<>(circleArea, HttpStatus.OK);
    }

    @RequestMapping(value = "/box", method = RequestMethod.POST)
    public ResponseEntity<?> findByBoxArea(@RequestBody BoxArea input) {
        List<Ubication> boxArea = service.findByBox(input.getStart(), input.getFinal());
        return new ResponseEntity<>(boxArea, HttpStatus.OK);
    }
}
