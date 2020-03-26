package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Document(collection = "symptom")
public class Symptom implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String document;

    private ZonedDateTime timestamp;

    private Double latitude;

    private Double longitude;

    private Boolean cough;
    private Boolean breathe;
    private int fever;

    public Symptom() { }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

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

    public Boolean getCough() {
        return cough;
    }

    public void setCough(Boolean cough) {
        this.cough = cough;
    }

    public Boolean getBreathe() {
        return breathe;
    }

    public void setBreathe(Boolean breathe) {
        this.breathe = breathe;
    }

    public int getFever() {
        return fever;
    }

    public void setFever(int fever) {
        this.fever = fever;
    }
}
