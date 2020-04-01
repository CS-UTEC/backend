package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Province;

public interface ProvinceRepository extends MongoRepository<Province, String> {
    Province findByName(String name);
    Province findByUbigeo(Integer ubigeo);
}
