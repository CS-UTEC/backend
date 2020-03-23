package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserApp;

public interface UserAppRepository extends MongoRepository<UserApp, Long> {
    UserApp findByDocumentAndType(String document, String type);
    UserApp findByDocument(String document);
}
