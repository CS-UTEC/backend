package data.models;

public class SymptomModel {

    private String document;
    private Double longitude;
    private Double latitude;
    private Boolean cough;
    private Boolean breathe;
    private int fever;

    public SymptomModel() { }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Boolean getCough() {
        return cough;
    }

    public void setCough(Boolean cough) {
        this.cough = cough;
    }

    public Boolean getBreathe() {
        return breathe;
    }

    public void setBreathe(Boolean breathe) {
        this.breathe = breathe;
    }

    public int getFever() {
        return fever;
    }

    public void setFever(int fever) {
        this.fever = fever;
    }
}
