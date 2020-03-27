package services;

import data.entities.Ubication;
import data.entities.UserApp;
import data.models.MapUser;
import data.models.UbicationModel;
import data.repositories.UserAppRepository;
import data.repositories.UbicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapService {

    @Autowired
    private UserAppRepository appRepository;

    @Autowired
    private UbicationRepository repository;

    public List<MapUser> getUsers(String state){
        /* TO DO
        Map<String, MapUser> redUsers = new HashMap<>();

        for (Ubication item :repository.findAll()) {
            if (!redUsers.containsKey(item.getUser().getId())) {
              MapUser mapUser = new MapUser();
              mapUser.setUserAppId(item.getUser().getId());
              mapUser.setLongitude(item.getLocation().getX());
              mapUser.setLatitude(item.getLocation().getY());
              mapUser.setTimeStamp(item.getTimeStamp());
              redUsers.put(item.getUser().getId(), mapUser);
            } else {
              ZonedDateTime prev = redUsers.get(item.getUser().getId()).getTimeStamp();
              ZonedDateTime cur = item.getTimeStamp();
              if (prev.compareTo(cur) < 0) {
                MapUser mapUser = new MapUser();
                mapUser.setUserAppId(item.getUser().getId());
                mapUser.setLongitude(item.getLocation().getX());
                mapUser.setLatitude(item.getLocation().getY());
                mapUser.setTimeStamp(item.getTimeStamp());
                redUsers.put(item.getUser().getId(), mapUser);
              }
            }
        }

        List<MapUser> items = new ArrayList<>();
        for (MapUser item: redUsers.values()) {
          items.add(item);
        }
        return items;
        */
        return null;
    }

}

