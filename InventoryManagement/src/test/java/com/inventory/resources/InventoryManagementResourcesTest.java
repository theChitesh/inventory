package com.inventory.resources;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.inventory.domain.Stock;
import com.inventory.services.StockServices;
import com.inventory.utils.StockServiceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class InventoryManagementResourcesTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@MockBean
	private static StockServices stockServices;
	
	@MockBean
	private static StockServiceValidator serviceValidator;
	
	
	
	
	@Configuration
	public static class TestConfiguration {
		
		@Bean @Autowired
		public InventoryManagementResource fetchController() {
			return new InventoryManagementResource(stockServices, serviceValidator);
		}
		
	}
	
	@Autowired
	public InventoryManagementResource resource;
	
	/*@Bean @Autowired
	public InventoryManagementResource fetchController() {
		return new InventoryManagementResource(stockServices, serviceValidator);
	}*/
	
	@Before
	public void setUp() {
		
		
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	
	@Test
	public void testGetSelectedItem() throws Exception {
		
		Stock stock = new Stock();
		stock.setId(1);
		stock.setName("first");
		
		Mockito.when(stockServices.getSelectedItem(1)).thenReturn(stock);
		
		
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/stocks/1").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//				.andExpect(arg0)
		
	}
	

}
