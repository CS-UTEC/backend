package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserWeb;

public interface UserWebRepository extends MongoRepository<UserWeb, Long> {
    UserWeb findByUsernameAndPassword(String document, String type);
    UserWeb findByUsername(String username);
}
