package com.danna.purchase.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApplicationError {
	
	HttpStatus httpStatus;
	List<String> errors;
	
	public ApplicationError(HttpStatus httpStatus, String errorMessage) {
		this.httpStatus = httpStatus;
		this.errors = List.of(errorMessage);
	}

}
