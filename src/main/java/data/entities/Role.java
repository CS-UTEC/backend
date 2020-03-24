package data.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
