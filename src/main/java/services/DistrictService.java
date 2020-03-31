package services;

import data.entities.District;
import data.repositories.DistrictRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository repository;

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
