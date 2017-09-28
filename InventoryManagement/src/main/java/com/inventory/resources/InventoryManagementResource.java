package com.inventory.resources;


import java.util.List;

import javax.validation.Valid;

import com.inventory.domain.Stock;
import com.inventory.services.Message;
import com.inventory.services.StockServices;
import com.inventory.utils.StockException;
import com.inventory.utils.StockServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryManagementResource {

  private final StockServices stockServices;
  private final StockServiceValidator serviceValidator;

  @Autowired
  public InventoryManagementResource(final StockServices stockServices,
                                     final StockServiceValidator serviceValidator) {
    this.stockServices = stockServices;
    this.serviceValidator = serviceValidator;
  }

  @GetMapping("/test")
  public String testService() {
    System.out.println("in here");
    return "Hello";

  }


  @GetMapping("/stocks")
  public List<Stock> getAllItems() {
    System.out.println("inside get");
    return stockServices.getAllStocks();
  }

  @PostMapping("/stocks")
  public ResponseEntity addStock(@RequestBody @Valid Stock stock) {
    Message messages = new Message();
    int id = stockServices.addStock(stock);
    Stock stk = new Stock();
    stk.setId(id);
    messages.setResult(stk);
    return ResponseEntity.ok(messages);
  }

  @GetMapping("/exp")
  public ResponseEntity expHandling() {
    throw new StockException("Error accured");
  }

  // @Pattern(regexp = "[0-9]+", message = "The id must be a valid number")
  //@ExceptionHandler(StockExceptionHandler.class)
  @RequestMapping(method = RequestMethod.POST, value = "/stocks/{id}")
  public ResponseEntity updateStock(@PathVariable("id") final String id, @RequestBody final Stock stock) {
    System.out.println("inside update " + id);

    System.out.println("inside update " + stock.getName());

    //try {
    stockServices.updateStock(Integer.parseInt(id), stock);
    /*} catch (StockException e) {
      System.out.println("in excpeiotn");
			throw new WebServiceException(e);
		}*/
    //		serviceValidator.validateForUpdate(stock);
    return ResponseEntity.noContent().build();
  }

	/*
  @ExceptionHandler(StockException.class)
	public ResponseEntity<StockException> exceptionHandler(Exception exp){
			
		StockException sexp = new StockException();
		sexp.setErrorMessage("This is a test");
		sexp.setErrorCode("001");
		return new ResponseEntity<StockException>(sexp,HttpStatus.OK);
	}*/
}
