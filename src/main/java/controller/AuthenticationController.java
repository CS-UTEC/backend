package controller;

import services.UserWebService;
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
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserWebService webService;

    @Autowired
    private UserAppService appService;

    @RequestMapping(value = "/web", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> webLogin(@RequestBody LoginWeb loginUser) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final UserWeb user = webService.findOneByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateTokenForWeb(user);
        return new ResponseEntity<>(new AuthToken(user.getId().toString(), token), HttpStatus.OK);
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> appLogin(@RequestBody LoginApp loginUser) {
        UserApp user = appService.findOrCreate(
            loginUser.getDocument(),
            loginUser.getType(),
            loginUser.getPublicityId()
        );
        return new ResponseEntity<>(new AuthToken(user.getId().toString(), null), HttpStatus.OK);
    }
}
