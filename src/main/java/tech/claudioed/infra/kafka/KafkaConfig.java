package tech.claudioed.infra.kafka;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.message.Encoding;
import io.cloudevents.jackson.JsonFormat;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class KafkaConfig {
  @ConfigProperty(name = "equipment-metadata.kafka.bootstrap-servers", defaultValue = "localhost:9092")
  String kafkaBrokers;
  @ConfigProperty(name = "equipment-metadata.kafka.clientId", defaultValue = "zug-partners")
  String kafkaClientId;
  @Produces
  @ApplicationScoped
  public Producer<String, CloudEvent> createProducer() {
    var props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaBrokers);
    props.put(ProducerConfig.CLIENT_ID_CONFIG, this.kafkaClientId);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
    props.put(CloudEventSerializer.ENCODING_CONFIG, Encoding.STRUCTURED);
    props.put(CloudEventSerializer.EVENT_FORMAT_CONFIG, JsonFormat.CONTENT_TYPE);
    KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props);
    return producer;
  }

}
