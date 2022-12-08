package kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class Consumers {
	
	@Autowired
	Environment env;
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory1()
	{
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.consumer.bootstrap-servers"));
//	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//	    		JsonSerializer.class);
//	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//	    		StringDeserializer.class);
	    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");

	    return new DefaultKafkaConsumerFactory<>(props);
	}


	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory1()
	{
	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory1());
	    factory.setConcurrency(Integer.valueOf(env.getProperty("spring.kafka.concurrency")));
	    
	    factory.getContainerProperties().setAckMode(AckMode.MANUAL);

	    return factory;
	}
	
//	//consumer 2
//	
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory2()
//	{
//	    Map<String, Object> props = new HashMap<>();
//	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.consumer.bootstrap-servers"));
//	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//	    		StringSerializer.class);
//	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//	    		JsonSerializer.class);
//	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-2");
//	    
//	    return new DefaultKafkaConsumerFactory<>(props);
//	}
//
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory2()
//	{
//	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	    factory.setConsumerFactory(consumerFactory2());
//	    factory.setConcurrency(Integer.valueOf(env.getProperty("spring.kafka.concurrency")));
//	    factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
//
//	    return factory;
//	}
//	
//	//consumer 3
//	
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory3()
//	{
//	    Map<String, Object> props = new HashMap<>();
//	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.consumer.bootstrap-servers"));
//	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//	    		StringSerializer.class);
//	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//	    		JsonSerializer.class);
//	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-3");
//	    
//	    return new DefaultKafkaConsumerFactory<>(props);
//	}
//
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory3()
//	{
//	    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	    factory.setConsumerFactory(consumerFactory3());
//	    factory.setConcurrency(Integer.valueOf(env.getProperty("spring.kafka.concurrency")));
//	    factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
//
//	    return factory;
//	}
	
	
}