package tech.claudioed.infra.ce;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MediaType;
import java.net.URI;
import java.util.UUID;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import tech.claudioed.domain.equipment.Measurement;
import tech.claudioed.domain.equipment.repository.MeasurementRepository;
import tech.claudioed.infra.ce.CExtensions.Audience;
import tech.claudioed.infra.ce.CExtensions.EventContext;

@ApplicationScoped
public class CloudEventRepository implements MeasurementRepository {

  private final ObjectMapper mapper;

  private final Producer<String, CloudEvent> ceProducer;

  public CloudEventRepository(ObjectMapper mapper, Producer<String, CloudEvent> ceProducer) {
    this.mapper = mapper;
    this.ceProducer = ceProducer;
  }
  @Override
  public void measure(Measurement point) {
    CloudEvent event = CloudEventBuilder.v1()
        .withId(UUID.randomUUID().toString())
        .withType(Measurement.EVENT_TYPE)
        .withSource(URI.create(point.source()))
        .withSubject(point.subject())
        .withDataContentType(MediaType.APPLICATION_JSON)
        .withData(PojoCloudEventData.wrap(point, mapper::writeValueAsBytes))
        .withExtension(CExtensions.AUDIENCE.name(), Audience.EXTERNAL_BOUNDED_CONTEXT.audienceName())
        .withExtension(CExtensions.EVENT_CONTEXT.name(), EventContext.DOMAIN.eventContextName())
        .build();
    try {
      RecordMetadata metadata = this.ceProducer.send(new ProducerRecord<>("leads", event.getId(), event))
          .get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
