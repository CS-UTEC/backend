package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserApp;

public interface UserAppRepository extends MongoRepository<UserApp, String> {
    UserApp findByDocumentAndType(String document, String type);
    UserApp findByPublicityId(String publicityId);
}
