package kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kafka.model.Email;
import utils.CustomLogger;


 
@Component
public class TopicListeners  {
	
	@Autowired
	Environment env;
	
	
	CustomLogger logger = new CustomLogger();
	
//	public void receiveMessageGroup3(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {
//		//LOGGER.info("received payload='{}'", consumerRecord.toString());
////		System.out.println(consumerRecord.toString());
//		System.out.println("recibe consumer 3");
//
//		acknowledgment.acknowledge();
//	}


//    @EventListener(condition = "event.listenerId.startsWith('lsr1-')")
//    public void eventHandler(ListenerContainerIdleEvent event) {
//        
//    }

//	@KafkaListener(id = "#{'${spring.kafka.consumer.group-id}'}", topics = "#{'${spring.kafka.consumer.topic}'.split(',')}", containerFactory = "kafkaContainerFactory1", concurrency = "#{'${spring.kafka.concurrency}'}")
//	public void receive(ConsumerRecord<?, ?> consumerRecord) {
//		//LOGGER.info("received payload='{}'", consumerRecord.toString());
//		System.out.println(consumerRecord.toString());
//
//	}
	
//	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
//			, groupId = "group-1")
//	public void receiveMessageGroup1(Acknowledgment acknowledgment) {
//		//LOGGER.info("received payload='{}'", consumerRecord.toString());
////		System.out.println(consumerRecord.toString());
//		System.out.println("recibe consumer 1");
//		acknowledgment.acknowledge();
//	}
//
//	
//	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
//			, groupId = "group-2")
//	public void receiveMessageGroup2( Acknowledgment acknowledgment) {
//		//LOGGER.info("received payload='{}'", consumerRecord.toString());
////		System.out.println(consumerRecord.toString());
//		System.out.println("recibe consumer 2");
//
//		acknowledgment.acknowledge();
//	}
//	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
//			, groupId = "group-3")
//	public void receiveMessageGroup3(   ) {
// 
//		System.out.println("recibe consumer 3");
//
//		//acknowledgment.acknowledge();
//	}
	//containerFactory = "kafkaListenerContainerFactory1"
 
	//listeners
	
	
//	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
//			, groupId = "group-1")
//	public void receiveMessageGroup1(@Payload String email, Acknowledgment acknowledgment   ) {
// 
//		System.out.println("recibe consumer 1");
//
//		acknowledgment.acknowledge();
//	}
//	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
//			, groupId = "group-2")
//	public void receiveMessageGroup2(@Payload String email, Acknowledgment acknowledgment   ) {
// 
//		System.out.println("recibe consumer 2");
//
//		acknowledgment.acknowledge();
//	}
	@KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", concurrency = "#{'${spring.kafka.concurrency}'}"
			, groupId = "group-3")
	public void receiveMessageGroup3(ConsumerRecord<?, ?> consumerRecord,  
	        Acknowledgment acknowledgment  ) {
 
		System.out.println("recibe consumer 3" );
		Gson gson = new Gson();
		Email email = gson.fromJson(consumerRecord.value().toString(), Email.class);
		
		String msg = email.genMessage();
		String emailMsg = email.getEmail();
		 
		
		logger.logInFile("mensaje: "+msg + "email: " +emailMsg);
		
		acknowledgment.acknowledge();
	}
	
	
 
}
