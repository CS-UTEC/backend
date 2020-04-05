package data.models;

public class MapStadistics {
  Integer day;
  Integer month;
  Integer year;
  String ubigeo;

  public MapStadistics() {}

  public void setDay(Integer day) {
      this.day = day;
  }

  public Integer getDay() {
      return this.day;
  }

  public void setMonth(Integer month) {
      this.month = month;
  }

  public Integer getMonth() {
      return this.month;
  }

  public void setYear(Integer year) {
      this.year = year;
  }

  public Integer getYear() {
      return this.year;
  }

  public void setUbigeo(String ubigeo) {
      this.ubigeo = ubigeo;
  }

  public String getUbigeo() {
      return this.ubigeo;
  }
}

