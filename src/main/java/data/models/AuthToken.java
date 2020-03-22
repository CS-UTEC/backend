package data.models;

public class AuthToken {

    private Long userId;
    private String token;

    public AuthToken() {}

    public AuthToken(Long userId, String token){
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
