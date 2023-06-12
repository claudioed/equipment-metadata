package tech.claudioed.domain.equipment.repository;

import tech.claudioed.domain.equipment.Measurement;

public interface MeasurementRepository {

  void measure(Measurement point);

}
