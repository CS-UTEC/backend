package services;

import data.entities.Ubication;
import data.repositories.UbicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UbicationService {

    @Autowired
    private UbicationRepository repository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<Ubication> findAll(){
        List<Ubication> items = new ArrayList<>();

        for (Ubication item :repository.findAll()) {
            items.add(item);
        }
        return items;
    }

    public Ubication findOne(long id){
        return repository.findById(id).get();
    }

    public Ubication create(Ubication item){
        item.setId(sequenceGeneratorService.generateSequence(Ubication.SEQUENCE_NAME));
        item.setTimeStamp(ZonedDateTime.now(ZoneOffset.UTC));
        return repository.save(item);
    }

    public Ubication update(Ubication item){
        Ubication i = repository.findById(item.getId()).get();
        for (Field f : item.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(item) == null) {
                    f.set(item, f.get(i));
                }
            } catch (IllegalAccessException e) {
                // shouldn't happen because I used setAccessible
            }
        }
        return repository.save(item);
    }

    public void delete(Long id){
        repository.delete(findOne(id));
    }
}
