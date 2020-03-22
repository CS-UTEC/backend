package data.entities;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_app")
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String SEQUENCE_NAME = "user_app_sequence";

    @Id
    private long id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String document;

    private String type;

    private Boolean canMove;

    @DBRef
    private Role role;

    public UserApp() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getCanMove() {
        return this.canMove;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }
}
