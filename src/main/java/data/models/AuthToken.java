package data.models;

public class AuthToken {

    private String userId;
    private String token;

    public AuthToken() {}

    public AuthToken(String userId, String token){
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
