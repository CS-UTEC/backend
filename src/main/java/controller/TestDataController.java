package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import services.AuthService;
import services.RoleService;
import data.entities.Role;
import data.entities.UserWeb;
import data.entities.UserApp;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestDataController {
    final static String clientUrl = "*";
    
    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    /*
    @RequestMapping(value = "/fake-users", method = RequestMethod.Get) {
        // UserApp user1 = authService.create();
    }
    */

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String generateData() {
        Role web = new Role("USER_WEB");
        roleService.create(web);

        Role app = new Role("USER_APP");
        roleService.create(app);

        Role role = roleService.findOneByName("USER_WEB");

        UserWeb usuario = new UserWeb();
        usuario.setUsername("admin@cs");
        usuario.setPassword("qwerty");
        usuario.setRole(role);
        authService.save(usuario);
        return "OK";
    }
}
