package data.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import com.mongodb.client.model.geojson.Point;

@Document(collection = "ubication")
public class Ubication implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String SEQUENCE_NAME = "ubication_sequence";

    @Id
    private long id;

    private ZonedDateTime timeStamp;

    @JsonIgnore
    private GeoJsonPoint location;

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

    public GeoJsonPoint getLocation() {
        return this.location;
    }

    public void setLocation(Double longitude, Double latitude) {
        this.location = new GeoJsonPoint(longitude, latitude);
    }

    public UserApp getUser() {
        return this.user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }
}
