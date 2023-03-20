package com.nitin.employeeservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	protected ResponseEntity<ErrorDetails> handlResourceNotFoundExceptione(ResourceNotFoundException ex,  WebRequest request) {
		
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), request.getDescription(false) , "400", ex.getMessage());
		
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}
}
