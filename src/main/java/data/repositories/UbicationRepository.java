package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Ubication;

public interface UbicationRepository extends MongoRepository<Ubication, ObjectId> {
}
