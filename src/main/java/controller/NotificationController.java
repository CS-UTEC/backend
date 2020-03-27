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

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public ResponseEntity<?> notify(@RequestBody NotificationModel body) {
        notificationService.create(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOrCreate(loginUser.getDocument(), loginUser.getType());
        return new ResponseEntity<>(notificationService.getAll(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public ResponseEntity<?> mark(@RequestBody NotificationMark notification) {
        UserApp user = appService.findOrCreate(notification.getDocument(), notification.getType());
        notificationService.update(notification.getNotificationId(), notification.getChecked());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/report-case", method = RequestMethod.POST)
    public ResponseEntity<?> reportCase(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOrCreate(loginUser.getDocument(), loginUser.getType());
        // TO DO
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
