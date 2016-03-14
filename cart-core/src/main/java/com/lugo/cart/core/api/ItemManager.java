package com.lugo.cart.core.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
	
	private final static BigDecimal NORMAL_TAX = new BigDecimal(0.10);

	private final static BigDecimal IMPORTED_TAX = new BigDecimal(0.05);
	
	public static ItemPurchased doCalculations(ItemPurchased item) {
	
		
		ItemPurchased itemP = new ItemPurchased(item, item.isImported(), item.getQuantity());
		
		try {
			
			BigDecimal totalTaxes = BigDecimal.ZERO;
			BigDecimal taxes = BigDecimal.ZERO;
			
			if (item.getItemType() == ItemType.GENERIC) {
				taxes = roundTaxes(item.getPrice().multiply(NORMAL_TAX));
			}
			if (item.isImported()) {
				taxes = roundTaxes(item.getPrice().multiply(IMPORTED_TAX).add(taxes));
				
			}
			itemP.setPriceTaxed(item.getPrice().add(taxes).setScale(2, RoundingMode.HALF_UP));
			
			totalTaxes =  taxes.multiply(item.getQuantity()).setScale(2, RoundingMode.HALF_UP);

			itemP.setTotalTaxes(totalTaxes);
			itemP.setTotal(item.getPrice().multiply(item.getQuantity()).add(totalTaxes).setScale(2, RoundingMode.HALF_UP));
			
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
		}
		
		
		return itemP;
		
	}

	private static BigDecimal roundTaxes(BigDecimal value) {
//		
//		BigDecimal rounded = value;
//		
//		Double roundedD = rounded.doubleValue();
//		
//		roundedD = Math.round(roundedD * 20.0) / 20.0;
//
//		rounded = new BigDecimal(roundedD).setScale(2,RoundingMode.HALF_UP);

		BigDecimal rounded = value.setScale(2, RoundingMode.HALF_UP);
		
		if (rounded.toString().indexOf(".") >= 0) {
			String decimals = rounded.toString().substring(rounded.toString().indexOf(".") + 1);
			
			int centsToAdd = 0; 
			
			Integer decimal = Integer.valueOf(decimals);
			if (decimal > 10 && decimal % 10 != 0) {
				if (decimal % 5 != 0) {
					int cent = Integer.valueOf(decimal.toString().substring(1));
					
					if (cent < 5) {
						centsToAdd = 5 - cent;
					} else if (cent > 5) {
						centsToAdd = 10 - cent;
					}
					
				} 
			} 
			
			if (centsToAdd > 0) {
				double dRound = Double.valueOf(rounded.toString());
				dRound = dRound + new Double("0.0" + centsToAdd);
				rounded = new BigDecimal(dRound);			
			}
		}
		
		
		return rounded.setScale(2, RoundingMode.HALF_UP);
		
	}
	
	public static List<ItemPurchased> doCalculations(List<ItemPurchased> items) {
	
		List<ItemPurchased> itemsP = new ArrayList<ItemPurchased>();
		if (items != null) {
			for (ItemPurchased item : items) {
				itemsP.add(doCalculations(item));
			}
		}		
		return itemsP;
		
	}
	
	
	
}
