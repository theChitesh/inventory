package com.inventory.utils;

public class StockException extends Exception{
	
	
	public StockException() {
		super();
	}
	public StockException(String errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	private String errorMessage;
	
	private String errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
