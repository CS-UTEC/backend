package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Rol;

public interface RolRepository extends MongoRepository<Rol, Long> {
    Rol findRolByName(String name);
}
