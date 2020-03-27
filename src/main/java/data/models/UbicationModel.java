package data.models;

public class UbicationModel {

    private String document;
    private String type;
    private Double longitude;
    private Double latitude;

    public UbicationModel() {}

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

    public String getDocument(){
        return this.document;
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

}
