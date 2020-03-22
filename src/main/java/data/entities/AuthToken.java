package data.entities;

public class AuthToken {

    private String token;
    private String username;
    private String rol;

    public AuthToken() {}

    public AuthToken(String token, String username, String rol){
        this.token = token;
        this.username = username;
        this.rol = rol;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
