package com.LoggerUtils.fallback;

import org.springframework.web.servlet.function.EntityResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoggerFallback {

	String error = "ERROR EN EL CUSTOM LOGGER  ";

	/**
	 * Método fallback para el servicio de guarda en logCircuitBreaker.
	 * 
	 * @param e           Error
	 * @return null , traza en log
	 */
	public EntityResponse<?> logInFileFallback( Throwable e) {
		log.error(error + e.getCause());
		return null;
	}
	
}



 