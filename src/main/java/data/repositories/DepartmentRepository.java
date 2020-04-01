package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByName(String name);
    Department findByUbigeo(Integer ubigeo);
}
