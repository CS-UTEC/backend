package services;

import data.entities.Ubication;
import data.entities.UserApp;
import data.models.MapUser;
import data.models.UbicationModel;
import data.models.MapReport;
import data.repositories.UserAppRepository;
import data.repositories.UbicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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

    @Autowired
    private MongoTemplate mongoTemplate;

    Boolean equal (UserApp user1, UserApp user2) {
        return user1.getDepartamento().equals(user2.getDepartamento()) &&
               user1.getProvincia().equals(user2.getProvincia()) &&
               user1.getDistrito().equals(user2.getDistrito());
    }

    public List<MapReport> getUsers(MapUser body){
        Query query = new Query();
        query.addCriteria(Criteria.where("state").is(body.getState()));
        /*
        query.addCriteria(Criteria.where("timeStamp.dateTime")
                                  .gte(body.getFrom())
                                  .lte(body.getTo()));
        */
        ArrayList <UserApp> users = new ArrayList <>();
        for (UserApp item: mongoTemplate.find(query, UserApp.class)) {
            users.add(item);
        }
        List <MapReport> report = new ArrayList <>();
        for (int i = 0; i < users.size(); i++) {
            int j = i;
            while (j + 1 < users.size() &&
                   equal(users.get(i), users.get(j + 1))) {
                j++;
            }
            MapReport item = new MapReport();
            item.setDepartamento(users.get(i).getDepartamento());
            item.setProvincia(users.get(i).getProvincia());
            item.setDistrito(users.get(i).getDistrito());
            item.setCasos(j - i + 1);
            report.add(item);
            i = j;
        }
        return report;
    }

}

