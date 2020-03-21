package data.entities;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    long id;

    String name;

    public Rol() {}

    public Rol(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
