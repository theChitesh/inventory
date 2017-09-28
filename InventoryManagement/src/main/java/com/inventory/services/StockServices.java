package com.inventory.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.inventory.domain.Stock;
import com.inventory.repository.StockRepository;
import com.inventory.utils.StockException;
import com.inventory.utils.StockServiceValidator;


@Service
@ComponentScan("com.inventory.*")
public class StockServices {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockServiceValidator stockValidator;
	
	/*@Autowired
	private BusinessRules businessRules;*/
	
	
	public void addItemInInventory(Stock item) {
		stockRepository.save(item);
	}

	public List<Stock> getAllStocks() {
		List<Stock> stocks = new ArrayList<>();
//		stockRepository.findAll().forEach(stocks::add);
		
		Iterator<Stock> it = stockRepository.findAll().iterator();
		while(it.hasNext()) {
			Stock stk = it.next();
			//businessRules.calculateDurationOfStockInDays(stk)
			
			System.out.println("Date "+stk.getName());
			System.out.println("Date "+stk.getEntryDate());
			
			
			stocks.add(stk);
		}
		
		
		return stocks;
	}
	
	
	public int addStock(Stock stock) {
		stock.setEntryDate(new Date());
		Stock stk = stockRepository.save(stock);
		System.out.println("ID after saving: "+stk.getEntryDate());
		return stk.getId();
	}
	
	
	public void updateStock(int id, Stock stock) throws StockException {
		
		System.out.println("stock "+stock.getName());
		
		Stock availableStock = stockRepository.findOne(id);
		
		stockValidator.validateForUpdate(availableStock, stock);
		
		availableStock.setId(id);
		availableStock.setLastUpdateDate(new Date());
		stockRepository.save(stock);
		
	}
}
