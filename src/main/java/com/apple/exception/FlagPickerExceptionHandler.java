package com.apple.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FlagPickerExceptionHandler {  
   
	
	@ExceptionHandler(ContinentNotFoundException.class)
	public ResponseEntity<String> handleContinentNotFoundException(HttpServletRequest request, Exception ex){
		
		 String message="Continent Not Found.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<String> handleCountryNotFoundException(HttpServletRequest request, Exception ex){
		 
		 String message="Country Not Found.";
		 return ResponseEntity.badRequest().body(message);				
	}
	
	@ExceptionHandler(JsonParsingException.class)
	public ResponseEntity<String> handleJsonParsingException(HttpServletRequest request, Exception ex){
		
		 String message="Exception while parsing the json string.";
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
}
