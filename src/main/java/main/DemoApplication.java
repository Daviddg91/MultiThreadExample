package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"kafka"})
@EnableKafka
public class DemoApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}

}
