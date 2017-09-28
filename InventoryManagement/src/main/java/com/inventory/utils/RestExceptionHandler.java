package com.inventory.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ResponseEntity<String> handleAccessDeniedException(
      final Throwable e
  ) {
    // TODO : have a error dto to return more details for failure
    LOGGER.info("Internal error accured", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(StockException.class)
  @ResponseBody
  public ResponseEntity<String> handleAccessDeniedException(
      final StockException e
  ) {
    LOGGER.info("HTTP 403 Access forbidden", e);
    return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.FORBIDDEN);
  }
}
