package data.repositories;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Ubication;

public interface UbicationRepository extends MongoRepository<Ubication, String> {
    List<Ubication> findByLocationWithin(Circle circle);
    List<Ubication> findByLocationWithin(Box box);
}
