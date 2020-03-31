package controller;

import services.UbicationService;
import services.UserAppService;
import data.entities.*;
import data.models.*;
import config.Constants;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.json.JSONException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ubication")
public class UbicationController {

    @Autowired
    private UbicationService ubicationService;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/interval", method = RequestMethod.GET)
    public ResponseEntity<?> interval() {
        HashMap <String, Integer> map = new HashMap();
        map.put("interval", Constants.INTERVAL_UBICATION_REQUEST);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<?> report(@RequestBody UbicationModel ubication) {
        UserApp user = appService.findOrCreate(ubication.getDocument(),
                                               ubication.getType());
        ubicationService.create(user, ubication.getLatitude(),
                                      ubication.getLongitude());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-data", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteData(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOrCreate(loginUser.getDocument(), loginUser.getType());
        ubicationService.deleteData(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
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
    */
}
