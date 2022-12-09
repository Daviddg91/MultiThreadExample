package kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class Producer {

	@Autowired
	Environment env;


	final KafkaProperties kafkaProperties;

	public Producer(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	@Bean
	public Map<String, Object> producerConfiguration() {
		Map<String, Object> properties = new HashMap(kafkaProperties.buildProducerProperties());
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				env.getProperty("spring.kafka.consumer.bootstrap-servers"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return properties;
	}

	@Bean
	ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory(producerConfiguration());
	}

	@Bean
	KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate(producerFactory());
	}

	@Bean
	public NewTopic topic() {
		String topic = env.getProperty("spring.kafka.consumer.topic");
		return new NewTopic(topic, Integer.valueOf(env.getProperty("spring.kafka.partitions")), (short) 1);
	}

}