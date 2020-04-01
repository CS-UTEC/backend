package data.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "province")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private Integer ubigeo;

    private String department;

    private GeoJsonPolygon geometry;

    public Province() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbigeo() {
        String code = String.format("%04d", this.ubigeo);
        return code;
    }

    public void setUbigeo(Integer ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public GeoJsonPolygon getGeometry() {
        return this.geometry;
    }

    public void setGeometry(GeoJsonPolygon geometry) {
        this.geometry = geometry;
    }
}
