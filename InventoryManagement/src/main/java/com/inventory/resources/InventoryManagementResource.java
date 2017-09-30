package com.inventory.resources;

import java.util.List;

import javax.validation.Valid;

import com.inventory.domain.Stock;
import com.inventory.services.Message;
import com.inventory.services.StockServices;
import com.inventory.utils.StockException;
import com.inventory.utils.StockServiceValidator;

import org.apache.derby.iapi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest resource class to deal which handles the REST operation on the stock resource
 * @author chitesh
 *
 */
@RestController
@RequestMapping("/stocks")
public class InventoryManagementResource {

	private final StockServices stockServices;
	private final StockServiceValidator serviceValidator;

	@Autowired
	public InventoryManagementResource(final StockServices stockServices,
			final StockServiceValidator serviceValidator) {
		this.stockServices = stockServices;
		this.serviceValidator = serviceValidator;
	}

	
	@GetMapping
	public List<Stock> getAllItems() {
		return stockServices.getAllStocks();
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/{id}")
	public Stock getSelectedItems(@PathVariable("id") final String id) {
		System.out.println("inside get");
		return stockServices.getSelectedItem(Integer.parseInt(id));
	}

	@PostMapping
	public ResponseEntity addStock(@RequestBody @Valid Stock stock) {
		
		Message messages = new Message();
		int id = stockServices.addStock(stock);
		Stock stk = new Stock();
		stk.setId(id);
		messages.setResult(new Integer(stk.getId()));
		
		return ResponseEntity.ok(messages);
	}

	@GetMapping("/exp")
	public ResponseEntity expHandling() {
		throw new StockException("Error accured");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity updateStock(@PathVariable("id") final String id, @RequestBody final Stock stock) {
		
		stockServices.updateStock(Integer.parseInt(id), stock);
		return ResponseEntity.noContent().build();
	}

	}
