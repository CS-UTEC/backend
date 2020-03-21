package data.entities;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String email;

    private String password;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolId", nullable = true)
    private Rol rol;
    */

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /*
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }
    */

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
