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
import java.util.Date;

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

    public Integer getNumberCases (Integer ubigeo, String state, ZonedDateTime from, ZonedDateTime to) {
        Query query = new Query();
        query.addCriteria(Criteria.where("state").is(state));
        query.addCriteria(Criteria.where("timeStamp")
                                  .gte(from)
                                  .lte(to));
        Department department = departmentRepository.findByUbigeo(ubigeo);
        if (department != null) {
          query.addCriteria(Criteria.where("departamento").
                            is(department.getName()));
        }
        Province province = provinceRepository.findByUbigeo(ubigeo);
        if (province != null) {
          query.addCriteria(Criteria.where("departamento").
                            is(province.getDepartment()));
          query.addCriteria(Criteria.where("provincia").
                            is(province.getName()));
        }
        District district = districtRepository.findByUbigeo(ubigeo);
        if (district != null) {
          query.addCriteria(Criteria.where("departamento").
                            is(district.getDepartment()));
          query.addCriteria(Criteria.where("provincia").
                            is(district.getProvince()));
          query.addCriteria(Criteria.where("distrito").
                            is(district.getName()));
        }
        List <UserApp> users = mongoTemplate.find(query, UserApp.class);
        return users.size();
    }

    public HashMap <String, List<MapReport>> getUsers(MapUser body){
        List<MapReport> report_confirmed = new ArrayList<>();
        List<MapReport> report_recovered = new ArrayList<>();
        List<MapReport> report_neutral = new ArrayList<>();
        ZonedDateTime from = body.getFrom();
        ZonedDateTime to = body.getTo();
        for (District district: districtRepository.findAll()) {
            String ubigeo_str = district.getUbigeo();
            Integer ubigeo = Integer.parseInt(ubigeo_str);
            Integer confirmed_cases = getNumberCases(ubigeo, "confirmed", from, to);
            if (confirmed_cases != 0) {
              report_confirmed.add(new MapReport(ubigeo_str, confirmed_cases));
            }
            Integer recovered_cases = getNumberCases(ubigeo, "recovered", from, to);
            if (recovered_cases != 0) {
              report_recovered.add(new MapReport(ubigeo_str, recovered_cases));
            }
            Integer neutral_cases = getNumberCases(ubigeo, "neutral", from, to);
            if (neutral_cases != 0) {
              report_neutral.add(new MapReport(ubigeo_str, neutral_cases));
            }
        }
        HashMap <String, List <MapReport>> report = new HashMap();
        report.put("confirmed", report_confirmed);
        report.put("recovered", report_recovered);
        report.put("neutral", report_neutral);
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
          query.addCriteria(Criteria.where("departamento").
                            is(province.getDepartment()));
          query.addCriteria(Criteria.where("provincia").
                            is(province.getName()));
        }
        District district = districtRepository.findByUbigeo(ubigeo);
        if (district != null) {
          query.addCriteria(Criteria.where("departamento").
                            is(district.getDepartment()));
          query.addCriteria(Criteria.where("provincia").
                            is(district.getProvince()));
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

