package data.models;

import org.springframework.data.geo.Point;

public class CircleArea {

    private Double centerX;
    private Double centerY;

    private Double radius;

    public CircleArea() {}

    public Point getCenter() {
        return new Point(centerX, centerY);
    }

    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
