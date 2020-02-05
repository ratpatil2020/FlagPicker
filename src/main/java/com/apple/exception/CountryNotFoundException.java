package com.apple.exception;

public class CountryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 9184430881300736479L;

	public CountryNotFoundException() {
		super();
	}
	
	public CountryNotFoundException(String message) {
		super(message);
	}
}
