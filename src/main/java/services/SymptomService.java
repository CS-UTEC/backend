package services;

import data.entities.Symptom;
import data.models.SymptomModel;
import data.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository repository;

    public Symptom create(SymptomModel item){
        Symptom s=new Symptom();
        s.setDocument(item.getDocument());
        s.setTimestamp(ZonedDateTime.now());
        s.setLatitude(item.getLatitude());
        s.setLongitude(item.getLongitude());
        s.setBreathe(item.getBreathe());
        s.setCough(item.getCough());
        s.setFever(item.getFever());

        return repository.save(s);
    }

}
