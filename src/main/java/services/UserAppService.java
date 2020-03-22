package services;

import data.entities.UserApp;
import data.models.LoginApp;
import data.repositories.RoleRepository;
import data.repositories.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserAppService {

    @Autowired
    private UserAppRepository repository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<UserApp> findAll(){
        List<UserApp> items = new ArrayList<>();

        for (UserApp item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public UserApp findOne(long id){
        return repository.findById(id).get();
    }

    public UserApp create(UserApp item){
        item.setId(sequenceGeneratorService.generateSequence(UserApp.SEQUENCE_NAME));
        item.setRole(roleRepository.findByName("USER_APP"));
        return repository.save(item);
    }

    public UserApp create(LoginApp item){
        UserApp userApp = new UserApp();
        userApp.setId(sequenceGeneratorService.generateSequence(UserApp.SEQUENCE_NAME));
        userApp.setRole(roleRepository.findByName("USER_APP"));
        userApp.setType(item.getType());
        userApp.setDocument(item.getDocument());
        userApp.setPhone(item.getPhone());
        return repository.save(userApp);
    }

    public UserApp update(UserApp item){
        return repository.save(item);
    }

    public void delete(Long id){
        repository.delete(findOne(id));
    }

    public UserApp findOneByDocumentAndType(String document, String type){
        return repository.findByDocumentAndType(document, type);
    }
}
