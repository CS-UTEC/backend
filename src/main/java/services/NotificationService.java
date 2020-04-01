package services;

import data.entities.Notification;
import data.entities.Department;
import data.entities.Province;
import data.entities.District;
import data.repositories.NotificationRepository;
import data.repositories.UserAppRepository;
import data.repositories.DepartmentRepository;
import data.repositories.ProvinceRepository;
import data.repositories.DistrictRepository;
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
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Notification> findAll(){
        List<Notification> items = new ArrayList<>();

        for (Notification item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public List<Notification> getAll(UserApp user){
        Query query = new Query();
        query.addCriteria(Criteria.where("user.id").is(user.getId()));
        // query.addCriteria(Criteria.where("checked").is(false));
        query.with(Sort.by(Sort.Direction.DESC, "timeStamp.dateTime"));
        return mongoTemplate.find(query, Notification.class);
    }

    public Notification findOne(String id){
        return repository.findById(id).get();
    }

    public Notification update (String id, Boolean checked) {
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
