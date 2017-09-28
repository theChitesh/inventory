package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.inventory", "com.inventory.utils.StockServiceValidator.class"})
@ComponentScan("com.inventory")
public class InventoryManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryManagementApplication.class, args);

	}

}
