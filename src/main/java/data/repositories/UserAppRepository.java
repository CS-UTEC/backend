package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.UserApp;

public interface UserAppRepository extends MongoRepository<UserApp, ObjectId> {
    UserApp findByDocumentAndType(String document, String type);
    UserApp findByDocument(String document);
}
