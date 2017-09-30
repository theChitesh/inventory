package com.inventory.services;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;
import com.inventory.utils.Constants;

/**
 * Class used to calculate the Business rules for the each of the stock items in Inventory.
 * @author chitesh
 *
 */
@Service
public class BusinessRules {

	
	
	
	
	public int getStockDurationInInventory(LocalDate entryDate) {

		LocalDate today = LocalDate.now();
		Period interval = Period.between(entryDate, today);
		return interval.getDays();
	}
	
	
	public boolean  isPromotionApplicable(int duration) {
		
		if(duration >= Constants.ACTIVATE_PROMOTION_AFTER_TEN_DAYS) {
			return true;
		}
		return false;
		
	}
	
	
	public boolean indicateForAdditionalOrder(Stock stock) {
		
		if(Constants.STOCK_LESS_THAN_HUNDRED_UNITS > stock.getQuantity()) {
			return true;
		}
		return false;
	}
	
	
	public int calculateInventoryCost(final int amountPerday, final int duration ) {
		int cost = amountPerday * duration;
		return cost;
	}
	
}
