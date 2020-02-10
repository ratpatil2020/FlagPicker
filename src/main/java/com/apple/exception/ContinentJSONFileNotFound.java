package com.apple.exception;

public class ContinentJSONFileNotFound extends RuntimeException{
	
	private static final long serialVersionUID = -7373520042909435965L;

	private String message;
	
	public ContinentJSONFileNotFound() {
		super();
		this.message="File Not Found Exception.";;
	}

	public ContinentJSONFileNotFound(String message) {
		super(message);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
