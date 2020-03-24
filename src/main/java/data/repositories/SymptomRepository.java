package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Symptom;

public interface SymptomRepository extends MongoRepository<Symptom, String> {
}
