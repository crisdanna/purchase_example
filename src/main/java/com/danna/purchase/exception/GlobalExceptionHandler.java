package com.danna.purchase.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	private HttpHeaders httpHeaders;
	
	@Autowired
	public GlobalExceptionHandler(@Qualifier("httpHeadersJSON") HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	
	public ApplicationError createError(HttpStatus httpStatus, String errorMessage) {
		return new ApplicationError(httpStatus, errorMessage);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<String> handleException(Exception exception){
		log.error("handleException:{}", exception);
		
		final var applicationError = createError(HttpStatus.INTERNAL_SERVER_ERROR, "Exception occurred.");
		return new ResponseEntity<>("Errors: " + applicationError.getErrors(), this.httpHeaders, applicationError.getHttpStatus());
	}
}
