package data.repositories;

import data.entities.Symptom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SymptomRepository extends MongoRepository<Symptom,Long> {
}
