package com.inventory.utils;

import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;

@Service
public class StockServiceValidator {

	
	public void validateForUpdate(Stock availableStock , Stock newStock) throws StockException {
		
		
		if(null == availableStock) {
//			System.out.println("No stock is available for update");
			throw new StockException("No stock is available for update");
		}
		

		System.out.println("Avaiale "+availableStock.getName());
		System.out.println("New "+newStock.getName());
		if(!availableStock.getName().equals(newStock.getName())) {
			System.out.println("Throw error, not working");
			throw new StockException("Stock Name can not be changed !");
		}
		
		
	}
}
