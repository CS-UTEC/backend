package services;

import data.entities.Symptom;
import data.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository repository;

    public Symptom create(Symptom item){
        //item.setId(sequenceGeneratorService.generateSequence(Ubication.SEQUENCE_NAME));
        item.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC));
        return repository.save(item);
    }

}
