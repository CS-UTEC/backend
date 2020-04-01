package services;

import data.entities.District;
import data.repositories.DistrictRepository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<District> findAll(){
        List<District> items = new ArrayList<>();

        for (District item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public District findOneByName(String name){
        return repository.findByName(name);
    }

    public District findOne(String id){
        return repository.findById(id).get();
    }

    public District findOneByPointIntersects(Double longitude, Double latitude) {
        Point point = new Point(new Position(longitude, latitude));
        MongoCollection<Document> department = mongoTemplate.getCollection("district");
        FindIterable<Document> result = department.find(Filters.geoIntersects("geometry", point));

        if (result.first() == null) {
            return null;
        }

        return findOne(result.first().get("_id").toString());
    }

    public District create(District item){
        return repository.save(item);
    }

    public District update(District item){
        District i = repository.findById(item.getId()).get();
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
