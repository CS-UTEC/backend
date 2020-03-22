package controller;

import services.UserAppService;
import config.JwtTokenUtil;
import data.entities.*;
import data.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> registration(@RequestBody LoginApp loginUser) {
        final UserApp item = appService.findOneByDocumentAndType(loginUser.getDocument(), loginUser.getType());
        if (item != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final UserApp user = appService.create(loginUser);
        final String token = jwtTokenUtil.generateTokenForApp(user);
        return new ResponseEntity<>(new AuthToken(user.getId(), token), HttpStatus.OK);
    }
}
