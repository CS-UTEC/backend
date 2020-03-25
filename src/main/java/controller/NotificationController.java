package controller;

import services.NotificationService;
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
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public ResponseEntity<?> getRedUsers(@RequestBody NotificationModel body) {
        notificationService.create(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all/{notificationId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable String notificationId) {
        return new ResponseEntity<>(notificationService.getAll(notificationId), HttpStatus.OK);
    }


    @RequestMapping(value = "/mark/{notification_id}/{state}", method = RequestMethod.GET)
    public ResponseEntity<?> mark(@PathVariable String notification_id, @PathVariable String state) {
        notificationService.update(notification_id, state);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
