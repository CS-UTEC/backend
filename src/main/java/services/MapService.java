package services;

import data.entities.Ubication;
import data.entities.UserApp;
import data.entities.Notification;
import data.entities.Department;
import data.entities.Province;
import data.entities.District;
import data.models.MapUser;
import data.models.UbicationModel;
import data.models.MapReport;
import data.repositories.UserAppRepository;
import data.repositories.UbicationRepository;
import data.repositories.NotificationRepository;
import data.repositories.DepartmentRepository;
import data.repositories.ProvinceRepository;
import data.repositories.DistrictRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.lang.reflect.Field;
import java.time.ZoneId;
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
    private NotificationRepository notificationRepository;

    @Autowired
    private UbicationRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

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
        query.addCriteria(Criteria.where("timeStamp")
                                  .gte(body.getFrom())
                                  .lte(body.getTo()));
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


    public void notifyRegion (Integer ubigeo, String message) {
        Query query = new Query();
        Department department = departmentRepository.findByUbigeo(ubigeo);
        if (department != null) {
          query.addCriteria(Criteria.where("departamento").
                            is(department.getName()));
        }
        Province province = provinceRepository.findByUbigeo(ubigeo);
        if (province != null) {
          query.addCriteria(Criteria.where("provincia").
                            is(province.getName()));
        }
        District district = districtRepository.findByUbigeo(ubigeo);
        if (district != null) {
          query.addCriteria(Criteria.where("distrito").
                            is(district.getName()));
        }

        for (UserApp user: mongoTemplate.find(query, UserApp.class)) {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setChecked(false);
            notification.setTimeStamp(ZonedDateTime.now());
            notification.setUser(user);
            notificationRepository.save(notification);
        }
    }

    public GeoJsonPolygon getPolygon (Integer ubigeo) {
        Department department = departmentRepository.findByUbigeo(ubigeo);
        if (department != null) {
            return department.getGeometry();
        }
        Province province = provinceRepository.findByUbigeo(ubigeo);
        if (province != null) {
            return province.getGeometry();
        }
        District district = districtRepository.findByUbigeo(ubigeo);
        if (district != null) {
            return district.getGeometry();
        }
        return null;
    }

}

