package data.models;

public class LoginApp {

    private String document;
    private String type;

    protected LoginApp() {}

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
