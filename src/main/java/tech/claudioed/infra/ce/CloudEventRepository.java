package tech.claudioed.infra;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.util.UUID;
import tech.claudioed.domain.equipment.Measurement;
import tech.claudioed.domain.equipment.repository.MeasurementRepository;

@ApplicationScoped
public class CloudEventRepository implements MeasurementRepository {
  @Override
  public void measure(Measurement point) {
    CloudEvent event = CloudEventBuilder.v1()
        .withId(UUID.randomUUID().toString())
        .withType(Measurement.EVENT_TYPE)
        .withSource(URI.create(point.source()))
        .withSubject()
        .withSource(URI.create("http://localhost"))
        .build();


  }

}
