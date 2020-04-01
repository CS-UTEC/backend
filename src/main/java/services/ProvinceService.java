package services;

import data.entities.Province;
import data.repositories.ProvinceRepository;

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
public class ProvinceService {

    @Autowired
    private ProvinceRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Province> findAll(){
        List<Province> items = new ArrayList<>();

        for (Province item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Province findOneByName(String name){
        return repository.findByName(name);
    }

    public Province findOne(String id){
        return repository.findById(id).get();
    }

    public Province findOneByPointIntersects(Double longitude, Double latitude) {
        Point point = new Point(new Position(longitude, latitude));
        MongoCollection<Document> department = mongoTemplate.getCollection("district");
        FindIterable<Document> result = department.find(Filters.geoIntersects("geometry", point));

        if (result.first() == null) {
            return null;
        }

        return findOne(result.first().get("_id").toString());
    }

    public Province create(Province item){
        return repository.save(item);
    }

    public Province update(Province item){
        Province i = repository.findById(item.getId()).get();
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
