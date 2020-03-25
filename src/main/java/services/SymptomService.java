package services;

import data.entities.Symptom;
import data.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository repository;

    public Symptom create(Symptom item){
        item.setTimestamp(ZonedDateTime.now());
        return repository.save(item);
    }

}
