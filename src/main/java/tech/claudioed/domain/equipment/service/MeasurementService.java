package tech.claudioed.domain.equipment.service;

import jakarta.enterprise.context.ApplicationScoped;
import tech.claudioed.domain.equipment.Measurement;
import tech.claudioed.domain.equipment.repository.MeasurementRepository;

@ApplicationScoped
public class MeasurementService {
  private final MeasurementRepository measurementRepository;
  public MeasurementService(MeasurementRepository measurementRepository) {
    this.measurementRepository = measurementRepository;
  }
  public void measure(Measurement point){
    this.measurementRepository.measure(point);
  }

}
