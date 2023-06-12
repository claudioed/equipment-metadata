package tech.claudioed.domain.equipment;

public class Equipment {
  private String code;
  private String name;
  private Location location;

  public Equipment(){}

  public Equipment(String code, String name, Location location) {
    this.code = code;
    this.name = name;
    this.location = location;
  }
  public Location getLocation() {
    return location;
  }
  public String getCode() {
    return code;
  }

}
