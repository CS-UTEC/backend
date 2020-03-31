package services;

import data.entities.Department;
import data.repositories.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> findAll(){
        List<Department> items = new ArrayList<>();

        for (Department item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Department findOneByName(String name){
        return repository.findByName(name);
    }

    public Department findOne(String id){
        return repository.findById(id).get();
    }

    public Department create(Department item){
        return repository.save(item);
    }

    public Department update(Department item){
        Department i = repository.findById(item.getId()).get();
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
