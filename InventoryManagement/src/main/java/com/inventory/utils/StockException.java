package com.inventory.utils;

/**
 * Exception Class needed for handling application exception
 * @author chitesh
 *
 */
public class StockException extends RuntimeException {

  public StockException(final String errorMessage) {
    super(errorMessage);
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
