package com.inventory.utils;

import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;

@Service
public class StockServiceValidator {

	
	public void validateForUpdate(Stock availableStock , Stock newStock) throws StockException {
		
		
		System.out.println("Avaiale "+availableStock.getName());
		System.out.println("New "+newStock.getName());
		
		
		if(availableStock.getName().equals(newStock.getName())) {
			System.out.println("Throw error");
			throw new StockException("error");
		}
		
		
	}
}
