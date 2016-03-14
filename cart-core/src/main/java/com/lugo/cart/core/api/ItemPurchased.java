package com.lugo.cart.core.api;

import java.math.BigDecimal;

public class ItemPurchased extends Item {
	
	private boolean isImported;
	
	private BigDecimal quantity;

	private BigDecimal total;

	private BigDecimal totalTaxes;

	private BigDecimal priceTaxed;
	
	public ItemPurchased(Item item, boolean isImported, BigDecimal quantity) {
		
		super(item.getCode(), item.getDescription(), item.getItemType(), item.getPrice());
		
		this.isImported = isImported;
		
		this.quantity = quantity;

		total = BigDecimal.ZERO;

		totalTaxes = BigDecimal.ZERO;

		priceTaxed = BigDecimal.ZERO;

	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPriceTaxed() {
		return priceTaxed;
	}
	
	public boolean isImported() {
		return isImported;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}

	protected void setTotal(BigDecimal value) {
		total = value;
	}

	protected void setPriceTaxed(BigDecimal value) {
		priceTaxed = value;
	}

	protected void setTotalTaxes(BigDecimal value) {
		totalTaxes = value;
	}	

}
