package com.lugo.cart.core.api;

import java.math.BigDecimal;

public class Item {
	
	private String code;
	
	private String description;

	private ItemType itemType;
	
	private BigDecimal price;
	
	public Item() {
		code = "";
		description = "";
		itemType = ItemType.GENERIC;
		price = BigDecimal.ZERO;
	}
	
	public Item(String code, String description, ItemType itemType, BigDecimal price) {
		this.code = code;
		this.description = description;
		this.itemType = itemType;
		this.price = price;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String value) {
		code = value;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String value) {
		description = value;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	
	public void setItemType(ItemType value) {
		itemType = value;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal value) {
		price = value;
	}
	
}
