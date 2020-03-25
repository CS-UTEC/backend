package data.models;

import org.springframework.data.geo.Point;

public class BoxArea {

    private Double StartX;
    private Double StartY;

    private Double FinalX;
    private Double FinalY;

    public BoxArea() {}

    public Point getStart() {
        return new Point(StartX, StartY);
    }

    public void setStartX(Double StartX) {
        this.StartX = StartX;
    }

    public void setStartY(Double StartY) {
        this.StartY = StartY;
    }

    public Point getFinal() {
        return new Point(FinalX, FinalY);
    }

    public void setFinalX(Double FinalX) {
        this.FinalX = FinalX;
    }

    public void setFinalY(Double FinalY) {
        this.FinalY = FinalY;
    }
}
