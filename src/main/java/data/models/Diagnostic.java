package data.models;

public class Diagnostic {

    private String message;
    private Integer score;
    
    public Diagnostic() {}

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
