package services;

import data.entities.Usuario;
import data.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<Usuario> findAll(){
        List<Usuario> items = new ArrayList<>();

        for (Usuario item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Usuario findOne(long id){
        return repository.findById(id).get();
    }

    public Usuario create(Usuario item){
        item.setId(sequenceGeneratorService.generateSequence(Usuario.SEQUENCE_NAME));
        return repository.save(item);
    }

    public Usuario update(Usuario item){
        return repository.save(item);
    }

    public void delete(Long id){
        repository.delete(findOne(id));
    }

    public Usuario findOneByEmail(String email){
        return repository.findUsuarioByEmail(email);
    }
}
