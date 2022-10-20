package com.demo.Exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler extends RuntimeException{
	
	private String errorMessage;
	
	public ExceptionHandler() {
		super();
	}

	public ExceptionHandler(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
