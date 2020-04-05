package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserApp;

public interface UserAppRepository extends MongoRepository<UserApp, String> {
    UserApp findByPublicityId(String publicityId);
}
