package com.inventory.resources;

import com.inventory.BaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InventoryManagementResourceTest extends BaseTest {

  @Test
  public void getStocks_WithoutElement_ShouldReturnOk() throws Exception {
    getAllStocks(ADMIN_TOKEN).andExpect(status().isOk());
  }

  @Test
  public void getStocks_WithoutElement_ShouldReturnEmptyList() throws Exception {
    final ResultActions allStocks = getAllStocks(ADMIN_TOKEN);
    allStocks.andExpect(status().isOk());
    final Stocks stocks = extractDtoFromMockResult(allStocks.andReturn(), Stocks.class);
    assertTrue(stocks.getStocks().isEmpty());
  }
}