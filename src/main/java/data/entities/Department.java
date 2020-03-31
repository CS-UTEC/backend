package data.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private Integer ubigeo;

    private GeoJsonPolygon geometry;

    public Department() {}

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

    public Integer getUbigeo() {
        return this.ubigeo;
    }

    public void setUbigeo(Integer ubigeo) {
        this.ubigeo = ubigeo;
    }

    public GeoJsonPolygon getGeometry() {
        return this.geometry;
    }

    public void setGeometry(GeoJsonPolygon geometry) {
        this.geometry = geometry;
    }
}
