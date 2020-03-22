package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Role;

public interface RoleRepository extends MongoRepository<Role, Long> {
    Role findRolByName(String name);
}
