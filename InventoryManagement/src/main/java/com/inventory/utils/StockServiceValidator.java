package com.inventory.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;

/**
 * Class used to validate the request data for further processing. 
 * @author chitesh
 *
 */
@Service
public class StockServiceValidator {

	/**
	 * Method used to verify the integer value of the ID.
	 * @param id - String input parameter
	 * @return integer value of ID
	 * @throws StockException - throws 
	 */
	public int validateId(final String id) throws StockException {
		
		if(!StringUtils.isNumeric(id)) {
			throw new StockException("Id can not be alphanumeric");
		}
		
		int parsedId = Integer.parseInt(id);
		
		return parsedId;
	}
	
	
	public void validateIdinParamAndPayload(int id , Stock stock) throws StockException{
		System.out.println("stock id "+stock.getId());
		if((null != stock.getId()) && (id != stock.getId())) {
			throw new StockException("Request body has a different ID than ID present in rquest URL.");
		}
	}
	
	public void checkIfStockIsPresent(List<Stock> stocks , Stock newStock) {
		
		boolean flag = stocks.stream().anyMatch(stock -> stock.getName().equals(newStock.getName()));
		if(flag) {
			throw new StockException("Stock with same name is already pesent.");
		}
	}
	
	
	public void validateForUpdate(Stock availableStock , Stock newStock) throws StockException {
		
		if(null == availableStock) {
			throw new StockException("No stock is available for update");
		}
		
		if(!availableStock.getName().equals(newStock.getName())) {
			throw new StockException("Stock Name can not be changed !");
		}
	}
}
