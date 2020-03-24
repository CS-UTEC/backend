package services;

import data.entities.Notification;
import data.repositories.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public List<Notification> findAll(){
        List<Notification> items = new ArrayList<>();

        for (Notification item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Notification findOne(String id){
        return repository.findById(id).get();
    }

    public Notification create(Notification item){
        item.setTimeStamp(ZonedDateTime.now(ZoneOffset.UTC));
        return repository.save(item);
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
