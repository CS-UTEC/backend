package services;

import data.entities.UserApp;
import data.models.LoginApp;
import data.repositories.RoleRepository;
import data.repositories.UserAppRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

@Service
@Transactional
public class UserAppService {

    @Autowired
    private UserAppRepository repository;
    
    @Autowired
    private RoleRepository roleRepository;

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
        if (document == null || type == null) {
            return null;
        }
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
     * Notify each user that during the last 'days' have been
     * near a confirmed case in a radio of 'radius' meter in a
     * interval of +- x hours
     */
    public void notifyPossibleCases (UserApp confirmedUser, 
                                     Double radius,
                                     Integer days,
                                     Integer interval,
                                     String message) {
        /* Algorithm notification pseudocode
          for (each user where user.state != "confirmed") {
            Boolean notify = false;
            for (each ubication of user of the last 'days') {
              Query query = new Query();
              query.addCriteria(places in a circle
                                of radio 'radius' around
                                ubication);
              query.addCriteria(timeStamp
                          .gt(ubication.timeStamp - 'interval')
                          .lt(ubication.timeStamp + 'interval'));
              if (mongoTemplate.findOne(query, Ubication.class)) {
                notify = true;
                break;
              }
              
            }
            if (notify) {
              notification.create(User, messag)
            }
          }
        */
    }

    public UserApp setConfirmed (UserApp user) {
        user.setState("confirmed");
        repository.save(user);
        String message = "Cuidado! Has estado cerca de un caso confirmado de COVID19";
//        notifyPossibleCases(user, 10, 7, 1, message);
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
