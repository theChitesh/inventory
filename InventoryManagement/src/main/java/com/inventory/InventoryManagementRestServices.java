package com.inventory;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.services.Message;
import com.inventory.services.Stock;
import com.inventory.services.StockServices;
import com.inventory.utils.StockException;
import com.inventory.utils.StockExceptionHandler;
import com.inventory.utils.StockServiceValidator;

@RestController
public class InventoryManagementRestServices {

	@Autowired
	private StockServices stockServices;
	
	@Autowired
	private StockServiceValidator serviceValidator;
	
	@RequestMapping("/test")
	public String testService() {
		System.out.println("in here");
		return "Hello";
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value = "/stocks")
	public List<Stock> getAllItems(){
		System.out.println("inside get");
		return stockServices.getAllStocks();
	}
	
	@RequestMapping(method = RequestMethod.POST, value= "/stocks")
	public ResponseEntity addStock(@RequestBody @Valid Stock stock, Errors errors) {
		
		Message messages = new Message();
		List<String> msgs = new ArrayList<>();
		if(errors.hasErrors()) {
			
			errors.getAllErrors().forEach(p -> msgs.add(p.getDefaultMessage()));
			messages.setMessage(msgs);
			
			return ResponseEntity.badRequest().body(messages);
			
		}
		
		
		int id = stockServices.addStock(stock);
		
		Stock stk = new Stock();
		stk.setId(id);
		messages.setResult(stk);
		return ResponseEntity.ok(messages);
	}
	// @Pattern(regexp = "[0-9]+", message = "The id must be a valid number")
	//@ExceptionHandler(StockExceptionHandler.class)
	@RequestMapping(method = RequestMethod.POST, value = "/stocks/{id}")
	public ResponseEntity updateStock(
			@PathVariable("id") String id
			, @RequestBody Stock stock){
		System.out.println("inside update "+id);
		
		System.out.println("inside update "+stock.getName());
		
		//try {
			stockServices.updateStock(Integer.parseInt(id),stock);
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
