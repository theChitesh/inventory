package com.inventory.services;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class Stock {

	@Id	
	@GeneratedValue
	private int id;

	@NotBlank(message = "stock name can not be empty!")
	private String name;
	
	
	@NotNull(message  ="quantity can not be null")
	@Min(value = 1)
	private Integer quantity;


	private boolean isPromotionActive;

	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM", timezone = "UTC")
	private Date entryDate;
	
	private String entryBy;

	@JsonFormat
	private Date lastUpdateDate;

	private String updatedBy;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	

	public boolean isPromotionActive() {
		return isPromotionActive;
	}

	public void setPromotionActive(boolean isPromotionActive) {
		this.isPromotionActive = isPromotionActive;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
}
