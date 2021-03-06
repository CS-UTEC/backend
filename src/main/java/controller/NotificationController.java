package controller;

import services.NotificationService;
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
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<?> getAll(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOneByPublicityId(loginUser.getPublicityId());
        return new ResponseEntity<>(notificationService.getAll(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public ResponseEntity<?> mark(@RequestBody NotificationMark notification) {
        notificationService.update(notification.getNotificationId(), notification.getChecked());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/report-case", method = RequestMethod.POST)
    public ResponseEntity<?> reportCase(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOneByPublicityId(loginUser.getPublicityId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        appService.setConfirmed(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('USER_WEB')")
    @RequestMapping(value = "/report-recover", method = RequestMethod.POST)
    public ResponseEntity<?> reportConfirmed(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOneByPublicityId(loginUser.getPublicityId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        appService.setRecovered(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
