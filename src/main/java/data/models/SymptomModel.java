package data.models;

import java.util.ArrayList;

public class SymptomModel {

    private String type;
    private String document;
    private ArrayList<Boolean> result;

    public SymptomModel() {
        result = new ArrayList<Boolean>(13);
    }

    public String getDocument() {
        return document;
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

    public ArrayList<Boolean> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Boolean> result) {
        this.result = result;
    }
}
