package services;

import data.entities.UserApp;
import data.entities.Ubication;
import data.entities.Notification;
import data.models.LoginApp;
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
import java.util.Calendar;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class UserAppService {

    @Autowired
    private UserAppRepository repository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserApp> findAll() {
        List<UserApp> items = new ArrayList<>();

        for (UserApp item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public UserApp findOne(String id){
        return repository.findById(id).get();
    }

    public UserApp create (String document, String type){
        UserApp user = new UserApp();
        // Raise exepctions here
        
        if (!(type.equals("DNI") ||
              type.equals("Pasaporte") ||
              type.equals("Carnet de Extranjería"))) {
            return null;
        }
        user = new UserApp();
        user.setRole(roleRepository.findByName("USER_APP"));
        user.setType(type);
        user.setDocument(document);
        user.setState("neutral");
        user.setTimeStamp(ZonedDateTime.now());
        return repository.save(user);       
    }

    public UserApp create (UserApp user){
        user.setRole(roleRepository.findByName("USER_APP"));
        return repository.save(user);       
    }

    public UserApp findOrCreate(String document, String type){
        UserApp user = findOneByDocumentAndType(document, type);
        if (user != null) return user;
        return create(document, type);
    }

    public UserApp setRecovered (UserApp user) {
        user.setState("recovered");
        return repository.save(user);
    }

    /*
     * Notify each user that a 'message' if during the last 'days' have been
     * near a confirmed case in a radio of 'radius' meter in a
     * interval of +- x hours
     */
    public void notifyPossibleCases (UserApp confirmedUser, 
                                     Double radius,
                                     Integer days,
                                     Integer interval,
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
                Notification notification = new Notification();
                notification.setMessage(message);
                notification.setChecked(false);
                notification.setTimeStamp(ZonedDateTime.now());
                notification.setUser(user);
                notificationRepository.save(notification);
            }
        }
    }

    public UserApp setConfirmed (UserApp user) {
        user.setState("confirmed");
        repository.save(user);
        Double radius = 10.0; // meters
        Integer days = 7; // days
        Integer interval = 24; // hours
        String message = "Cuidado! Has estado cerca de un caso confirmado de COVID19";
        notifyPossibleCases(user, radius, days, interval, message);
        return user;
    }

    public UserApp update(UserApp item){
        return repository.save(item);
    }

    public void delete(String id){
        repository.delete(findOne(id));
    }

    public UserApp findOneByDocumentAndType(String document, String type){
        return repository.findByDocumentAndType(document, type);
    }

    
}
