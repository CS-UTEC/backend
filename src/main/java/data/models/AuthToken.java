package data.models;

public class AuthToken {

    private Long id;
    private String token;

    public AuthToken() {}

    public AuthToken(Long id, String token){
        this.id = id;
        this.token = token;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
