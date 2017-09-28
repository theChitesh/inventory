package com.inventory.utils;

public class StockExceptionHandler extends Exception{
	
	public StockExceptionHandler() {
		super();
	}
	public StockExceptionHandler(String message) {
		super(message);
		this.message = message;
		
	}
	
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
