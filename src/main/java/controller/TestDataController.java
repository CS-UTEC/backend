package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import services.AuthService;
import services.DistrictService;
import services.RoleService;
import services.UserAppService;
import services.UbicationService;
import data.entities.District;
import data.entities.Role;
import data.entities.Ubication;
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

    @Autowired
    private DistrictService districtService;

    /*
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
    */

    private UserApp createUserApp(String document, District district) {
        if (district == null) {
            return null;
        }
        Random rand = new Random();
        UserApp userApp = new UserApp();
        userApp.setDocument(document);
        userApp.setType("DNI");
        userApp.setDepartamento(district.getDepartment());
        userApp.setProvincia(district.getProvince());
        userApp.setDistrito(district.getName());
        
        Integer p = rand.nextInt(11);

        if (p > 8) {
            userApp.setState("confirmed");
        } else {
            if (p > 5) {
                userApp.setState("recovered");
            } else {
                userApp.setState("neutral");
            }
        }
        return appService.create(userApp);
      }

    @RequestMapping(value = "/gen/{nUsers}", method = RequestMethod.GET)
    public String usersGenerator(@PathVariable Long nUsers) {
        District district = null;
        Random rand = new Random();
        int cod;
        Double lat, lon;
        for (int i = 0; i < nUsers; i++) {
            cod = rand.nextInt(89999999) + 10000000;
            lat = (rand.nextInt(15635723 - 3821286) + 3821286.0)/1000000;
            lon = (rand.nextInt(81947887 - 67767339) + 67767339.0)/1000000;
            district = districtService.findOneByPointIntersects(-lon, -lat);
            createUserApp(Integer.toString(cod), district);
        }
        return "OK";
    }

    @RequestMapping(value = "/gen/deleteall", method = RequestMethod.GET)
    public String usersDestructor() {
        for (UserApp userApp : appService.findAll()) {
            appService.delete(userApp.getId());
        }
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
