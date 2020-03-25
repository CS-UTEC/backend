package services;

import data.entities.Notification;
import data.models.NotificationModel;
import data.repositories.NotificationRepository;
import data.repositories.UserAppRepository;
import data.entities.UserApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserAppRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Notification> findAll(){
        List<Notification> items = new ArrayList<>();

        for (Notification item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public List<Notification> getAll(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("user.id").is(id));
        // query.addCriteria(Criteria.where("checked").is(false));
        query.with(Sort.by(Sort.Direction.DESC, "timeStamp"));
        return mongoTemplate.find(query, Notification.class);
    }

    public Notification findOne(String id){
        return repository.findById(id).get();
    }

    public void create(NotificationModel model){
        for (String userAppId: model.getUserAppId()) {
            Notification notification = new Notification();
            notification.setMessage(model.getMessage());
            notification.setChecked(false);
            notification.setTimeStamp(ZonedDateTime.now());
            UserApp user = userRepository.findById(userAppId).get();
            notification.setUser(user);
            repository.save(notification);
        } 
    }

    public Notification update (String id, String state) {
        Boolean checked = state.equals("true");
        Notification notification = repository.findById(id).get();
        notification.setChecked(checked);
        return repository.save(notification);
    }

    public Notification update(Notification item){
        Notification i = repository.findById(item.getId()).get();
        for (Field f : item.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(item) == null) {
                    f.set(item, f.get(i));
                }
            } catch (IllegalAccessException e) {
                // shouldn't happen because I used setAccessible
            }
        }
        return repository.save(item);
    }

    public void delete(String id){
        repository.delete(findOne(id));
    }
}
