package tech.claudioed.domain.equipment;

import java.util.List;
import java.util.Set;
import org.apache.kafka.common.protocol.types.Field.Str;

public class Measurement {
  public static final String EVENT_TYPE = "tech.claudioed.equipment.measurement.collected";
  public static final String SUBJECT = "new-measure-point";
  private static final String SOURCE_PATTERN = "locations/%s/equipments/%s";
  private Equipment equipment;
  private Set<Metadata> metadata;
  public Equipment getEquipment() {
    return equipment;
  }
  public Set<Metadata> getMetadata() {
    return metadata;
  }
  public String source(){
    return String.format(SOURCE_PATTERN,this.equipment.getLocation().getCode(),this.equipment.getCode());
  }
  public String subject(){
    return SUBJECT;
  }
  public void addEquipment(Equipment equipment){
    this.equipment = equipment;
  }
  public void addMetadata(Set<Metadata> metadata){
    this.metadata = metadata;
  }
}
