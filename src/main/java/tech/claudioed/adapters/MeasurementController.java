package tech.claudioed.adapters;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import tech.claudioed.domain.equipment.service.MeasurementService;
import tech.claudioed.ports.MeasurementData;

@Path("/measurements")
public class MeasurementController {
  private final MeasurementService measurementService;

  public MeasurementController(MeasurementService measurementService) {
    this.measurementService = measurementService;
  }
  @POST
  public void collect(MeasurementData measurementData){
    this.measurementService.measure(measurementData.newMeasurement());
  }

}
