package data.models;

public class MapReport {

    private String ubigeo;
    private Integer casos;

    public MapReport(String ubigeo, Integer casos) {
        this.ubigeo = ubigeo;
        this.casos = casos;
    }

    public String getUbigeo() {
        return this.ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Integer getCasos() {
        return this.casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

}
