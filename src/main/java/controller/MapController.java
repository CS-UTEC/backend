package controller;

import services.MapService;
import services.UserAppService;
import config.JwtTokenUtil;
import data.entities.*;
import data.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ResponseEntity<?> getUsers(@RequestBody MapUser body) {
        return new ResponseEntity<>(mapService.getUsers(body), HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/notify/{ubigeo}", method = RequestMethod.POST)
    public ResponseEntity<?> notifyRegion(@PathVariable Integer ubigeo, @RequestBody NotificationModel body) {
        mapService.notifyRegion(ubigeo, body.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/notify-region", method = RequestMethod.POST)
    public ResponseEntity<?> notifyRegion(@RequestBody NotificationRegion body) {
        mapService.notifyRegions(body.getUbigeos(), body.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/polygon/{ubigeo}", method = RequestMethod.GET)
    public ResponseEntity<?> polygon(@PathVariable Integer ubigeo) {
        return new ResponseEntity<>(mapService.getPolygon(ubigeo), HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/stadistics", method = RequestMethod.GET)
    public ResponseEntity<?> stadistics() {
        return new ResponseEntity<>(mapService.getStadistics(), HttpStatus.OK);
    }


}
