package kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kafka.model.Email;

@RequestMapping("/api/kafka")
@RestController
public class KafkaController {

	@Autowired
	Environment env;
	
	final KafkaTemplate kafkaTemplate;

	Logger logger = LoggerFactory.getLogger(KafkaController.class);

	public KafkaController(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public String sentMessage() {

		Email email = new Email();
		Email emailGenerate = email.generateOutputEmail();
 
		Gson gson = new Gson();
		String jsonObject = gson.toJson(emailGenerate);
		System.out.println("comienza a enviar mensajes");
		for (int i = 0; i < 10; i++) {
			this.kafkaTemplate.send(env.getProperty("spring.kafka.consumer.topic"), jsonObject);
		 	}

		return "Hello World!";
	}


}