package data.models;

import java.util.ArrayList;

public class SymptomModel {
    
    private String publicityId;
    private ArrayList<Boolean> result;

    public SymptomModel() {
        result = new ArrayList<Boolean>(13);
    }

    public String getPublicityId() {
        return publicityId;
    }

    public void setPublicityId(String publicityId) {
        this.publicityId = publicityId;
    }

    public ArrayList<Boolean> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Boolean> result) {
        this.result = result;
    }
}
