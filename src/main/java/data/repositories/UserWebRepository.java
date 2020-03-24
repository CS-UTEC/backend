package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserWeb;

public interface UserWebRepository extends MongoRepository<UserWeb, ObjectId> {
    UserWeb findByUsernameAndPassword(String document, String type);
    UserWeb findByUsername(String username);
}
