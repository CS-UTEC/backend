package data.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Document(collection = "ubication")
public class Ubication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    private ZonedDateTime timeStamp;

    @JsonIgnore
    private GeoJsonPoint location;

    @DBRef
    @JsonIgnore
    private UserApp user;

    public Ubication() {}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
