package com.inventory.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inventory.controller.InventoryManagementController;
import com.inventory.domain.Stock;
import com.inventory.services.StockServices;
import com.inventory.utils.StockServiceValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryManagementController.class)
public class Test2 {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockServiceValidator seviceValidator;
	
	@MockBean
	private StockServices stockService;
	
	@Test
	public void test() throws Exception {
		Stock stock = new Stock();
		
		stock.setId(1);
		stock.setName("first");
		Mockito.when(stockService.getSelectedItem(1)).thenReturn(stock);
		
		this.mockMvc.perform(get("/stocks/1")).andDo(print()).andExpect(status().isOk());
		
	}
	

}
