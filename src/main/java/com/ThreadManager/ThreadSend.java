package com.ThreadManager;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import com.LoggerUtils.CustomLogger;
import com.entitys.Email;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ThreadSend {

	@Autowired 
	CustomLogger logger; 
	
	@Async
	public Future<String> asyncMethodWithReturnType(String jsonObject) {
	    System.out.println("Execute method asynchronously - " 
	      + Thread.currentThread().getName());
	    try {
	    	this.asyncMethodWithReturnType(jsonObject);
	        Thread.sleep(5000);
	        return new AsyncResult<String>("hello world !!!!");
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    return null;
	}
	
	
 
	public void functionalSendMessage(String jsonObject) {

		Gson gson = new Gson();
		Email email = gson.fromJson(jsonObject, Email.class);

		String msg = email.genMessage();
		String emailMsg = email.getEmail();

		logger.logInFile("consumidor1: "  + "mensaje: " + msg + "email: " + emailMsg);

		

	}
	
}
