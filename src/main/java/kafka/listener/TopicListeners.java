package kafka.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kafka.model.Email;
import utils.CustomLogger;


 
@Component
public class TopicListeners  {
	
	@Autowired
	Environment env;
	
	
	CustomLogger logger = new CustomLogger();
	

	@KafkaListener( topics = "#{'${spring.kafka.consumer.topic}'}",
					containerFactory = "kafkaListenerContainerFactory3" )
			public void receiveMessages1(ConsumerRecord<?, ?> consumerRecord,  
	        Acknowledgment acknowledgment , @Header(KafkaHeaders.CONSUMER) Consumer<?, ?> consumer ) {
 
		
		Gson gson = new Gson();
		Email email = gson.fromJson(consumerRecord.value().toString(), Email.class);
		
		String msg = email.genMessage();
		String emailMsg = email.getEmail();
		String consumerMember = consumer.groupMetadata().memberId();
		
		logger.logInFile("consumidor1: " + consumerMember + "mensaje: "+msg + "email: " +emailMsg);
		
		System.out.println("aqui llegan , 1 ");
		acknowledgment.acknowledge();
	}
	
 
	@KafkaListener( topics = "#{'${spring.kafka.consumer.topic}'}",
			containerFactory = "kafkaListenerContainerFactory2" )
	public void receiveMessages2(ConsumerRecord<?, ?> consumerRecord,  
    Acknowledgment acknowledgment , @Header(KafkaHeaders.CONSUMER) Consumer<?, ?> consumer ) {


	Gson gson = new Gson();
	Email email = gson.fromJson(consumerRecord.value().toString(), Email.class);
	
	String msg = email.genMessage();
	String emailMsg = email.getEmail();
	String consumerMember = consumer.groupMetadata().memberId();
	
	logger.logInFile("consumidor2: " + consumerMember + "mensaje: "+msg + "email: " +emailMsg);

		System.out.println("aqui llegan , 2 ");
acknowledgment.acknowledge();
}
	@KafkaListener( topics = "#{'${spring.kafka.consumer.topic}'}",
			containerFactory = "kafkaListenerContainerFactory3" )
	public void receiveMessages3(ConsumerRecord<?, ?> consumerRecord,  
    Acknowledgment acknowledgment , @Header(KafkaHeaders.CONSUMER) Consumer<?, ?> consumer ) {


Gson gson = new Gson();
Email email = gson.fromJson(consumerRecord.value().toString(), Email.class);

String msg = email.genMessage();
String emailMsg = email.getEmail();
String consumerMember = consumer.groupMetadata().memberId();

logger.logInFile("consumidor3: " + consumerMember + "mensaje: "+msg + "email: " +emailMsg);

System.out.println("aqui llegan , 3 ");
acknowledgment.acknowledge();
}

 
}
