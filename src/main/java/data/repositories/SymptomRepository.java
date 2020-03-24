package data.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Symptom;

public interface SymptomRepository extends MongoRepository<Symptom, ObjectId> {
}
