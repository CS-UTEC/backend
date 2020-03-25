package data.models;

import java.time.ZonedDateTime;

public class MapUser {
    private ZonedDateTime timeStamp;
    private String userAppId;
    private Double latitude;
    private Double longitude;

    public MapUser() {}

    public void setUserAppId (String id) {
      this.userAppId = id;
    }

    public String getUsetAppId () {
      return this.userAppId;
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

}
