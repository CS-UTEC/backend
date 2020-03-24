package controller;

import services.MapService;
import services.UserAppService;
import config.JwtTokenUtil;
import data.entities.*;
import data.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/red-users", method = RequestMethod.GET)
    public ResponseEntity<?> getRedUsers() {
        return new ResponseEntity<>(mapService.getRedUsers(), HttpStatus.OK);
      
    }

}
