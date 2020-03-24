package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import services.AuthService;
import services.RoleService;
import services.UserAppService;
import services.UbicationService;
import data.entities.Role;
import data.entities.UserWeb;
import data.entities.UserApp;
import data.models.UbicationModel;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestDataController {
    final static String clientUrl = "*";
    
    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAppService appService; 

    @Autowired
    private UbicationService ubicationService;

    private UserApp createUserApp(String document, String phone) {
      UserApp userApp = new UserApp();
      userApp.setDocument(document);
      userApp.setType("dni");
      userApp.setPhone(phone);
      return appService.create(userApp);
    }

    private void createLocation(double latitude, double longitude, UserApp userApp) {    
        UbicationModel ubication = new UbicationModel();
        ubication.setLatitude(latitude);
        ubication.setLongitude(longitude);
        ubicationService.create(ubication, userApp);
    }

    @RequestMapping(value = "/fake-users", method = RequestMethod.GET)
    public String fakeUsers() {
        UserApp user1 = createUserApp("11111111", "12121212");
        UserApp user2 = createUserApp("22222222", "13131313");
        createLocation(12.2332, 24.2324, user1);
        createLocation(15.1235, 0.12134, user2);
        createLocation(40.2434, 43.3243, user1);
        createLocation(35.32434, 23.233, user2);
        return "OK";
    }

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
