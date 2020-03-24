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

    public List<UserApp> findAll() {
        List<UserApp> items = new ArrayList<>();

        for (UserApp item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public UserApp findOne(String id){
        return repository.findById(id).get();
    }

    public UserApp create(UserApp item){
        item.setRole(roleRepository.findByName("USER_APP"));
        UserApp user = repository.findByDocumentAndType(item.getDocument(), item.getType());
        if (user == null) {
          return repository.save(item);
        }
        return user;
    }

    public UserApp create(LoginApp item){
        if (item.getType() == null ||
            item.getDocument() == null ||
            item.getPhone() == null) {
            return null;
        }
        if (!(item.getType().equals("dni") ||
              item.getType().equals("passport") ||
              item.getType().equals("carnet"))) {
            return null;
        }
        UserApp userApp = new UserApp();
        userApp.setRole(roleRepository.findByName("USER_APP"));
        userApp.setType(item.getType());
        userApp.setDocument(item.getDocument());
        userApp.setPhone(item.getPhone());
        return repository.save(userApp);
    }

    public UserApp update(UserApp item){
        return repository.save(item);
    }

    public void delete(String id){
        repository.delete(findOne(id));
    }

    public UserApp findOneByDocumentAndType(String document, String type){
        return repository.findByDocumentAndType(document, type);
    }

    
}
