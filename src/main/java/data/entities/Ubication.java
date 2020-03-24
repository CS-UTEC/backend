package data.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ubication")
public class Ubication implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String SEQUENCE_NAME = "ubication_sequence";

    @Id
    private long id;

    private ZonedDateTime timeStamp;

    private Double latitude;

    private Double longitude;

    @DBRef
    @JsonIgnore
    private UserApp user;

    public Ubication() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTimeStamp(){
        return this.timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public UserApp getUser() {
        return this.user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }
}
