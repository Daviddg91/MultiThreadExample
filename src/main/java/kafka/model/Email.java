package kafka.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import utils.RandomString;

public class Email implements Serializable{

	private Date date;
	private String email;
	private String message;
	
	public Email() {}


    public Email generateOutputEmail() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		this.setDate(date);
		
		this.setEmail(getEmailAleatAlfaNumeric());
		this.setMessage(genMessage());
		
		return this;
    }

 public String getEmailAleatAlfaNumeric() {

	 RandomString randomStr = new RandomString();
	 String str = randomStr.randomString(7);
	 return str+"@test.com";
	 
 }
 
 private int getAleatNumeric() {
	 RandomString randomStr = new RandomString();
	 int str = randomStr.generateRandomNumber(7);
	 return str ;
 }

 public String genMessage() {
	 String msg = "Hola, tu código de acceso es "+ Integer.valueOf(getAleatNumeric()) + ".";
	 
	 
	 return msg;
 }
public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

 
}