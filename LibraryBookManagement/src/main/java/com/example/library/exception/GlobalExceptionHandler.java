package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBookNotFoundException(BookNotFoundException ex) {
	    return ex.getMessage();
	}
	
	@ExceptionHandler(DuplicateBookException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleDuplicateBookException(DuplicateBookException ex) {
	    return ex.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneralException(Exception ex) {
	    return ex.getMessage();
	}

}
