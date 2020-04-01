package data.models;

import java.util.ArrayList;

public class NotificationRegion {
    private String message;
    private ArrayList <Integer> ubigeos;

    public NotificationRegion() {}

    public void setMessage (String message) {
        this.message = message;
    }

    public String getMessage () {
        return this.message;
    }

    public void setUbigeos (ArrayList <Integer> ubigeos) {
        this.ubigeos = ubigeos;
    }

    public ArrayList <Integer> getUbigeos () {
        return this.ubigeos;
    }

};
