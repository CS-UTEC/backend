package data.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_web")
public class UserWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String username;

    private String password;

    @DBRef
    private Role role;

    public UserWeb() {}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRol() {
        return role;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
