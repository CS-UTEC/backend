package data.models;

public class MapReport {

    private String departamento;
    private String provincia;
    private String distrito;
    private Integer casos;

    public MapReport() {}

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return this.distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Integer getCasos() {
        return this.casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

}
