package services;

import data.entities.Role;
import data.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> findAll(){
        List<Role> items = new ArrayList<>();

        for (Role item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Role findOneByName(String name){
        return repository.findByName(name);
    }

    public Role findOne(String id){
        return repository.findById(id).get();
    }

    public Role create(Role item){
        return repository.save(item);
    }

    public Role update(Role item){
        Role i = repository.findById(item.getId()).get();
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
