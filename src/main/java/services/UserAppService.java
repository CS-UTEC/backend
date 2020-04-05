package services;

import data.entities.UserApp;
import data.entities.Ubication;
import data.entities.Notification;
import data.repositories.RoleRepository;
import data.repositories.UserAppRepository;
import data.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class UserAppService {

    @Autowired
    private UserAppRepository appRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserApp> findAll() {
        List<UserApp> items = new ArrayList<>();
        for (UserApp item: appRepository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public UserApp create(String document, String type, String publicityId){
        UserApp user = new UserApp();

        if (publicityId == null) {
            return null;
        }
        
        user.setRole(roleRepository.findByName("USER_APP"));
        user.setState("neutral");
        user.setTimeStamp(ZonedDateTime.now());
        user.setPublicityId(publicityId);

        if (type != null && document != null) {
            if (type.equals("DNI") ||
                type.equals("Pasaporte") ||
                type.equals("Carnet de Extranjería")) {
                user.setType(type);
                user.setDocument(document);
            }
        }
        return appRepository.save(user);       
    }

    public UserApp create(UserApp user){
        user.setRole(roleRepository.findByName("USER_APP"));
        return appRepository.save(user);       
    }

    public UserApp findOrCreate(String document, String type, String publicityId){
        UserApp user = findOneByPublicityId(publicityId);
        if (user != null) return user;
        return create(document, type, publicityId);
    }

    public UserApp setRecovered(UserApp user) {
        user.setState("recovered");
        createNotification(user, "Estado", "Caso recuperado");
        return appRepository.save(user);
    }

    /*
     * Notify each user that a 'message' if during the last 'days' have been
     * near a confirmed case in a radio of 'radius' meter in a
     * interval of +- x hours
     */
    public void notifyPossibleCases(UserApp confirmedUser, 
                                    Double radius,
                                    Integer days,
                                    Integer interval,
                                    String title,
                                    String message) {
        ZonedDateTime from = ZonedDateTime.now().minusDays(days);
        Query query = new Query();
        query.addCriteria(Criteria.where("state").ne("confirmed"));
        for (UserApp user: mongoTemplate.find(query, UserApp.class)) {
            Boolean notify = false;
            Query query_ubication = new Query();
            query_ubication.addCriteria(Criteria.where("timeStamp")
                                        .gte(from));
            query_ubication.addCriteria(Criteria.where("user.id")
                                        .is(user.getId()));
            for (Ubication ubication: mongoTemplate.find(query_ubication, Ubication.class)) {
                Query query_contact = new Query();
                query_contact.addCriteria(Criteria.where("location")
                                          .near(ubication.getLocation())
                                          .maxDistance(radius));
                query_contact.addCriteria(Criteria.where("user.id")
                                          .is(confirmedUser.getId()));
                query_contact.addCriteria(Criteria.where("timeStamp")
                                          .gte(ubication.getTimeStamp().minus(interval, ChronoUnit.HOURS))
                                          .lte(ubication.getTimeStamp().plus(interval, ChronoUnit.HOURS)));
                List <Ubication> ubications = mongoTemplate.find(query_contact, Ubication.class);
                if (!ubications.isEmpty()) {
                    notify = true;
                    break;
                }
            }
            if (notify) {
              createNotification(user, title, message);
            }
        }
    }

    public void createNotification(UserApp user, String title, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setChecked(false);
        notification.setTimeStamp(ZonedDateTime.now());
        notification.setUser(user);
        notification.setTitle("notification");
        notificationRepository.save(notification);
    }

    public UserApp setConfirmed(UserApp user) {
        user.setState("confirmed");
        appRepository.save(user);
        Double radius = 10.0; // meters
        Integer days = 7; // days
        Integer interval = 24; // hours
        String title = "Cuidado!";
        String message = "Has estado cerca de un caso confirmado de COVID19";
        notifyPossibleCases(user, radius, days, interval, title, message);
        createNotification(user, "Estado", "Caso confirmado");
        return user;
    }

    public UserApp update(UserApp item) {
        return appRepository.save(item);
    }

    public void delete(String id){
        appRepository.delete(findOneByPublicityId(id));
    }

    public UserApp findOneByPublicityId(String publicityId){
        return appRepository.findByPublicityId(publicityId);
    }
}
