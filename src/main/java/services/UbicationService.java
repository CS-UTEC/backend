package services;

import data.entities.Ubication;
import data.entities.UserApp;
import data.models.UbicationModel;
import data.repositories.UbicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;

@Service
public class UbicationService {

    @Autowired
    private UbicationRepository repository;

    public List<Ubication> findAll(){
        List<Ubication> items = new ArrayList<>();

        for (Ubication item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Ubication findOne(String id){
        return repository.findById(id).get();
    }

    public Ubication create(UbicationModel item, UserApp user){
        Ubication ubication = new Ubication();
        ubication.setTimeStamp(ZonedDateTime.now());
        ubication.setLocation(item.getLongitude(), item.getLatitude());
        ubication.setUser(user);
        return repository.save(ubication);
    }

    public Ubication update(Ubication item){
        Ubication i = repository.findById(item.getId()).get();
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

    public List<Ubication> findByCircle(Point center, Double radius) {
        return repository.findByLocationWithin(new Circle(center, radius));
    }
    
    public List<Ubication> findByBox(Point start, Point end) {
        return repository.findByLocationWithin(new Box(start, end));
    }
}

// Useful links:
// https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.geospatial
// https://github.com/eugenp/tutorials/blob/master/persistence-modules/java-mongodb/src/test/java/com/baeldung/geo/MongoGeospatialLiveTest.java
