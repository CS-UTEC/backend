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

    public UserApp findOrCreate(String document, String type){
      UserApp user = findOneByDocumentAndType(document, type);
      if (user != null) return user;
      // Raise exepctions here
      if (document == null || type == null) {
          return null;
      }
      if (!(type.equals("DNI") ||
            type.equals("Pasaporte") ||
            type.equals("Carnet de Extranjería"))) {
          return null;
      }
      user = new UserApp();
      user.setRole(roleRepository.findByName("USER_APP"));
      user.setType(type);
      user.setDocument(document);
      user.setState("neutral");
      return repository.save(user);       
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
