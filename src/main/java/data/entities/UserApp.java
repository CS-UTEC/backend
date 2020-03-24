package data.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_app")
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String document;

    private String type;

    private String phone;

    @DBRef
    private Role role;

    public UserApp() {}

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

    public String getDocument(){
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
