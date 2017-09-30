package com.inventory.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;
import com.inventory.repository.StockRepository;
import com.inventory.utils.StockException;
import com.inventory.utils.StockServiceValidator;

/**
 * Stock Service class used to validate the operate the CRUD operation on the resources
 * @author chitesh
 *
 */
@Service
@ComponentScan("com.inventory.*")
public class StockServices {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockServiceValidator stockValidator;
	
	@Autowired
	private BusinessRules businessRules;
	
	
	public List<Stock> getAllStocks() {
		List<Stock> stocks = new ArrayList<>();
//		stockRepository.findAll().forEach(stocks::add);
		
		Iterator<Stock> it = stockRepository.findAll().iterator();
		while(it.hasNext()) {
			Stock stock = it.next();
			
			
			int durationInDays = businessRules.getStockDurationInInventory(stock.getEntryDate());
			boolean promotionApplicable = businessRules.isPromotionApplicable(durationInDays);
			boolean additionalStock = businessRules.indicateForAdditionalOrder(stock);
			int costInInventory = businessRules.calculateInventoryCost(durationInDays , stock.getAmountPerDay());
			
			stock.setDaysInInventory(durationInDays);
			stock.setActivatePromotion(promotionApplicable);
			stock.setOrderAdditionalStock(additionalStock);
			stock.setInventoryCost(costInInventory);
			
			
			
			stocks.add(stock);
		}
		
		
		return stocks;
	}
	
	
	public Stock getSelectedItem(final int id) {
		
		System.out.println("in selected one");
		return stockRepository.findOne(id);
	}
	
	
	public int addStock(Stock stock) {
//		stock.setEntryDate(LocalDate.now());
		Stock stk = stockRepository.save(stock);
		return stk.getId();
	}
	
	
	public void updateStock(final int id, Stock stock) throws StockException {
		
		Stock availableStock = getSelectedItem(id);
		
		stockValidator.validateForUpdate(availableStock, stock);
		
//		availableStock.setId(id);
//		availableStock.setLastUpdateDate(LocalDate.now());
		stockRepository.save(stock);
		
	}
}
