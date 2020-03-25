package data.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private ZonedDateTime timeStamp;

    private String message;

    private Boolean checked;

    @DBRef
    private UserApp user;

    public Notification() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTimeStamp(){
        return this.timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public UserApp getUser() {
        return this.user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }
}
