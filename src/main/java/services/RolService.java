package services;

import data.entities.Rol;
import data.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public List<Rol> findAll(){
        List<Rol> items = new ArrayList<>();

        for (Rol item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Rol findOneByName(String name){
        return repository.findRolByName(name);
    }

    public Rol findOne(long id){
        return repository.findById(id).get();
    }

    public Rol create(Rol item){
        return repository.save(item);
    }

    public Rol update(Rol item){
        Rol i = repository.findById(item.getId()).get();
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

    public void delete(Long id){
        repository.delete(findOne(id));
    }


}
