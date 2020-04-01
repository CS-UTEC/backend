package data.models;

public class MapReport {

    private Integer ubigeo;
    private Integer casos;

    public MapReport(Integer ubigeo, Integer casos) {
        this.ubigeo = ubigeo;
        this.casos = casos;
    }

    public Integer getUbigeo() {
        return this.ubigeo;
    }

    public void setUbigeo(Integer ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Integer getCasos() {
        return this.casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

}
