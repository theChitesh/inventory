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

	
	public Stock enrichStockParameters(Stock stock) {
		
		int durationInDays = getStockDurationInInventory(stock.getEntryDate());
		boolean promotionApplicable = isPromotionApplicable(durationInDays);
		boolean additionalStock = indicateForAdditionalOrder(stock);
		int costInInventory = calculateInventoryCost(durationInDays , stock.getAmountPerDay());
		
		stock.setDaysInInventory(durationInDays);
		stock.setActivatePromotion(promotionApplicable);
		stock.setOrderAdditionalStock(additionalStock);
		stock.setInventoryCost(costInInventory);
		
		
		
		return stock;
	}
	
	
	private int getStockDurationInInventory(LocalDate entryDate) {

		LocalDate today = LocalDate.now();
		Period interval = Period.between(entryDate, today);
		return interval.getDays();
	}
	
	
	private boolean  isPromotionApplicable(int duration) {
		
		if(duration >= Constants.ACTIVATE_PROMOTION_AFTER_TEN_DAYS) {
			return true;
		}
		return false;
		
	}
	
	
	private boolean indicateForAdditionalOrder(Stock stock) {
		
		if(Constants.STOCK_LESS_THAN_HUNDRED_UNITS > stock.getQuantity()) {
			return true;
		}
		return false;
	}
	
	
	private int calculateInventoryCost(final int amountPerday, final int duration ) {
		int cost = amountPerday * duration;
		return cost;
	}
	
}
