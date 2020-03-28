package services;

import data.entities.UserApp;
import data.entities.Symptom;
import data.models.SymptomModel;
import data.models.Diagnostic;
import data.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository repository;

    public Diagnostic createAndGetDiagnostic(UserApp user, SymptomModel item){
        Symptom report = new Symptom();
        report.setResult(item.getResult());
        report.setTimestamp(ZonedDateTime.now());
        repository.save(report);
        Diagnostic diagnostic = new Diagnostic();
        Integer score = report.computeScore();
        String message = report.computeMessage(score);
        diagnostic.setScore(score);
        diagnostic.setMessage(message);
        return diagnostic;
    }

}
