package data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.ZonedDateTime;

import java.util.ArrayList;

@Document(collection = "symptom")
public class Symptom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private ZonedDateTime timestamp;
    private ArrayList<Boolean> result;

    @DBRef
    @JsonIgnore
    private UserApp user;

    public Symptom() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Boolean> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Boolean> result) {
        this.result = result;
    }

    public UserApp getUser() {
        return this.user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }

    public static String[] getQuestions () {
        String[] questions = {
            "¿Estás teniendo tos?",
            "¿Estás teniendo escalofríos?",
            "En este momento o en los días previos, ¿has tenido diarrea?",
            "¿Tienes dolor de garganta?",
            "¿Estás teniendo dolor de cuerpo y malestar general?",
            "¿Estás presentando dolores de cabeza?",
            "¿Has tenido fiebre? [para fines correctos más de 37.8° C]",
            "¿Has perdido el olfato?",
            "¿Estás teniendo dificultad para respirar? [como si no entrara el aire al pecho]",
            "¿Estás experimentando fatiga? [Real deterioro de tus movimientos y ganas de hacer algo]",
            "¿Has viajado en los últimos 14 días?",
            "¿Has viajado o estado en un área afectada por COVID19?",
            "¿Has estado en contacto directo o cuidado a algun paciente COVID19 positivo?"
        };
        return questions;
    }

    public int computeScore() {
        int[] cost = {
          1, // tos
          1, // escalofríos
          1, // diarrea
          1, // dolor gargante
          1, // malestar general
          1, // dolores de cabeza
          1, // fiebre
          1, // perder el olfato
          2, // dificultad para respirar
          2, // fatiga
          3, // haber viajado
          3, // haber estado en un área infectada
          3  // haber estado en contacto con un infectado
        };
        int score = 0;
        for (int i = 0; i < cost.length; i++) {
            int ret = this.result.get(i) ? 1 : 0;
            score += cost[i] * ret;
        }
        return score;
    }
    
    public String computeMessage(int score) {
        if (score <= 2) {
            return "Podría ser estrés, tome sus precauciones y observe";
        }
        if (score <= 5) {
            return "Hidrátese, conserve medidas de higiene, observe y reevalúe en 2 días";
        }
        if (score <= 11) {
            return "Acuda a consulta con el médico";
        }
        return "Llame a los servicios para realizar detección para SARS-COV2 (COVID 19)";
    }

}
