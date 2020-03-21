package data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import data.entities.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {
    Usuario findUsuarioByEmailAndPassword(String email, String passwd);
    Usuario findUsuarioByEmail(String email);
}
