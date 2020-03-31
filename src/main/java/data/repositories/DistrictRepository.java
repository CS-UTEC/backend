package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.District;

public interface DistrictRepository extends MongoRepository<District, String> {
    District findByName(String name);
}
