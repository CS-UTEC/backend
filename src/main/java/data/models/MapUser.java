package data.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class MapUser {
    private String state;
    private Date from;
    private Date to;

    public MapUser() {}

    public void setState (String state) {
        this.state = state;
    }

    public String getState () {
        return this.state;
    }

    public void setFrom (Long timestampFrom) {
        this.from = new Date(timestampFrom);
    }

    public ZonedDateTime getFrom () {
        return ZonedDateTime.ofInstant(this.from.toInstant(), ZoneId.systemDefault());
    }

    public void setTo (Long timestampTo) {
        this.to = new Date(timestampTo);
    }

    public ZonedDateTime getTo () {
        return ZonedDateTime.ofInstant(this.to.toInstant(), ZoneId.systemDefault());
    }


}
