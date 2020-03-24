package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Document(collection = "symptom")
public class Symptom implements Serializable {


    private static final long serialVersionUID = 1L;

    @Transient
    public static final String SEQUENCE_NAME = "symptom_sequence";

    @Id
    private long id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String document;

    private ZonedDateTime timestamp;

    private Double latitude;

    private Double longitude;

    private String simptom_1;
    private String simptom_2;
    private String simptom_3;
    private String simptom_4;

    public Symptom() { }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSimptom_1() {
        return simptom_1;
    }

    public void setSimptom_1(String simptom_1) {
        this.simptom_1 = simptom_1;
    }

    public String getSimptom_2() {
        return simptom_2;
    }

    public void setSimptom_2(String simptom_2) {
        this.simptom_2 = simptom_2;
    }

    public String getSimptom_3() {
        return simptom_3;
    }

    public void setSimptom_3(String simptom_3) {
        this.simptom_3 = simptom_3;
    }

    public String getSimptom_4() {
        return simptom_4;
    }

    public void setSimptom_4(String simptom_4) {
        this.simptom_4 = simptom_4;
    }
}
