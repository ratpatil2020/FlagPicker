package com.apple.exception;

public class ContinentNotFoundException extends RuntimeException  {

	
	private static final long serialVersionUID = -5402205637236137973L;

	public ContinentNotFoundException() {
		super();
	}
	
	public ContinentNotFoundException(String message) {
		super(message);
	}
}
