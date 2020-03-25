package data.models;

import java.util.ArrayList;

public class NotificationModel {
    private String message;
    private ArrayList <String> userAppId;

    public NotificationModel() {}

    public void setMessage (String message) {
        this.message = message;
    }

    public String getMessage () {
        return this.message;
    }

    public void setUserAppId (ArrayList <String> userAppId) {
        this.userAppId = userAppId;
    }

    public ArrayList <String> getUserAppId() {
        return this.userAppId;
    }
};
