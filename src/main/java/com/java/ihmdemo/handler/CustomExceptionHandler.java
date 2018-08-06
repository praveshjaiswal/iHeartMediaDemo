package com.java.ihmdemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	@ResponseBody
    public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex) {
		logger.info("***Logged message: "+ex.getMessage());
       
       ErrorMessage exceptionResponse = new ErrorMessage(ex.getMessage(), 
    		   "Please try after some time!");
       return new ResponseEntity<ErrorMessage>(exceptionResponse,
    		   new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

class ErrorMessage{
	private String message;
	private String details;

	public ErrorMessage(String message, String details){
		super();
		this.message= message;
		this.details= details;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	
}








