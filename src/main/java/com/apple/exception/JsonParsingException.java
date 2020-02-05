package com.apple.exception;

public class JsonParsingException extends RuntimeException {
	
	private static final long serialVersionUID = -867509905741884329L;

	public JsonParsingException() {
		super();
	}
	
	public JsonParsingException(String message) {
		super(message);
	}
}
