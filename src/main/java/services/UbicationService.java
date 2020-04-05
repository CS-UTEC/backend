package services;

import data.entities.Ubication;
import data.entities.District;
import data.entities.Province;
import data.entities.Department;
import data.entities.UserApp;
import data.repositories.UbicationRepository;
import data.repositories.UserAppRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Service
public class UbicationService {

    @Autowired
    private UbicationRepository repository;

    @Autowired
    private UserAppRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public String getDistrito (Double latitude, Double longitude) {
        Query query = new Query();      
        query.addCriteria(Criteria.where("geometry").intersects(new GeoJsonPoint(longitude, latitude)));
        List <District> districts = mongoTemplate.find(query, District.class);
        for (District district: districts) {
            return district.getName();
        }
        return null;
    }

    public String getProvincia (Double latitude, Double longitude) {
        Query query = new Query();      
        query.addCriteria(Criteria.where("geometry").intersects(new GeoJsonPoint(longitude, latitude)));
        List <Province> provinces = mongoTemplate.find(query, Province.class);
        for (Province province: provinces) {
            return province.getName();
        }
        return null;
    }

    public Department getDepartamento (Double latitude, Double longitude) {
        Query query = new Query();
        query.addCriteria(Criteria.where("geometry").intersects(new GeoJsonPoint(longitude, latitude)));
        List <Department> departments = mongoTemplate.find(query, Department.class);
        for (Department department: departments) {
            return department;
        }
        return null;
    }


    public Ubication create(UserApp user, Double latitude, Double longitude){
        Ubication ubication = new Ubication();
        ubication.setTimeStamp(ZonedDateTime.now());
        ubication.setLocation(longitude, latitude);
        user.setDistrito(getDistrito(latitude, longitude));
        user.setProvincia(getProvincia(latitude, longitude));
        Department department = getDepartamento(latitude, longitude);
        user.setDepartamento(department.getName());
        user.setUbigeo(department.getUbigeo());
        user.setTimeStamp(ZonedDateTime.now());
        userRepository.save(user);
        ubication.setUser(user);
        return repository.save(ubication);
    }

    public void deleteData(UserApp user){
        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(user));
        mongoTemplate.remove(query, Ubication.class);
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
