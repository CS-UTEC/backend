package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
