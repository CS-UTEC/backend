package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Role;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Role findByName(String name);
}
