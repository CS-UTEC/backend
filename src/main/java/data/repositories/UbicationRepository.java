package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Ubication;

public interface UbicationRepository extends MongoRepository<Ubication, Long> {
}
