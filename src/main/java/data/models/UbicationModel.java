package data.models;

public class UbicationModel {

    private String publicityId;
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

    public String getPublicityId() {
        return publicityId;
    }

    public void setPublicityId(String publicityId) {
        this.publicityId = publicityId;
    }
}
