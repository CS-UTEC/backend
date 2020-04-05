package services;

import data.entities.UserWeb;
import data.repositories.RoleRepository;
import data.repositories.UserWebRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserWebService {

    @Autowired
    private UserWebRepository webRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserWeb> findAll(){
        List<UserWeb> items = new ArrayList<>();
        for (UserWeb item: webRepository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public UserWeb findOne(String id){
        return webRepository.findById(id).get();
    }

    public UserWeb create(UserWeb item){
        item.setRole(roleRepository.findByName("USER_WEB"));
        return webRepository.save(item);
    }

    public UserWeb update(UserWeb item){
        return webRepository.save(item);
    }

    public void delete(String id){
        webRepository.delete(findOne(id));
    }

    public UserWeb findOneByUsername(String username){
        return webRepository.findByUsername(username);
    }
}
