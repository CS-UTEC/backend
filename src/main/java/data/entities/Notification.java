package data.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String SEQUENCE_NAME = "notification_sequence";

    @Id
    private long id;

    private ZonedDateTime timeStamp;

    private String message;

    private Boolean checked;

    @DBRef
    private List<UserApp> users;

    public Notification() {}

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

    public List<UserApp> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserApp> users) {
        this.users = users;
    }
}