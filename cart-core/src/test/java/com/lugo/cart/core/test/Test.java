package com.lugo.cart.core.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lugo.cart.core.api.Item;
import com.lugo.cart.core.api.ItemManager;
import com.lugo.cart.core.api.ItemPurchased;
import com.lugo.cart.core.api.ItemType;

public class Test {

	@org.junit.Test
	public void test() {
		List<ItemPurchased> items = null;
		Item item = null;
		ItemPurchased itemP = null;
		List<ItemPurchased> itemsTotals = null;

		//First Test
		items = new ArrayList<ItemPurchased>();
		item = new Item("LOR", "Lord of the Rings", ItemType.BOOK, new BigDecimal(12.49));
		itemP = new ItemPurchased(item, false, new BigDecimal(1));
		items.add(itemP);
		item = new Item("INN", "Innuendo", ItemType.GENERIC, new BigDecimal(14.99));
		itemP = new ItemPurchased(item, false, new BigDecimal(1));
		items.add(itemP);
		item = new Item("CHB", "Chocolate Bar", ItemType.FOOD, new BigDecimal(0.85));
		itemP = new ItemPurchased(item, false, new BigDecimal(1));
		items.add(itemP);
		itemsTotals = ItemManager.doCalculations(items);
		exposeResults(itemsTotals);

		//Second Test
		items = new ArrayList<ItemPurchased>();
		item = new Item("CHB", "Chocolate Bar", ItemType.FOOD, new BigDecimal(10));
		itemP = new ItemPurchased(item, true, new BigDecimal(1));
		items.add(itemP);
		item = new Item("PER", "Bottle of Perfume", ItemType.GENERIC, new BigDecimal(47.50));
		itemP = new ItemPurchased(item, true, new BigDecimal(1));
		items.add(itemP);
		itemsTotals = ItemManager.doCalculations(items);
		exposeResults(itemsTotals);		
		
		//Third Test
		items = new ArrayList<ItemPurchased>();
		item = new Item("PER", "Bottle of Perfume", ItemType.GENERIC, new BigDecimal(27.99));
		itemP = new ItemPurchased(item, true, new BigDecimal(1));
		items.add(itemP);
		item = new Item("PER1", "Bottle of Perfume", ItemType.GENERIC, new BigDecimal(18.99));
		itemP = new ItemPurchased(item, false, new BigDecimal(1));
		items.add(itemP);
		item = new Item("HDP", "Headache Pills", ItemType.MEDICINE, new BigDecimal(9.75));
		itemP = new ItemPurchased(item, false, new BigDecimal(1));
		items.add(itemP);
		item = new Item("CHO", "Box of chocolate", ItemType.FOOD, new BigDecimal(11.25)); //????? The nearest 0.05 of 0.5625 is 0.55. I'm missing something...
		itemP = new ItemPurchased(item, true, new BigDecimal(1));
		items.add(itemP);
		itemsTotals = ItemManager.doCalculations(items);
		exposeResults(itemsTotals);		
		
	}
	
	private void exposeResults(List<ItemPurchased> items) {
		
		if (items != null) {
			
			System.out.println("Results");
			
			BigDecimal totalTaxes = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			
			for (ItemPurchased item : items) {
				StringBuilder str = new StringBuilder();
				str.append(item.getQuantity());
				str.append(" ");
				str.append(item.getDescription());
				str.append(" : ");
				str.append(item.getPriceTaxed());
				
				totalTaxes = totalTaxes.add(item.getTotalTaxes());
				total = total.add(item.getTotal());
				
				System.out.println(str.toString());
			}
			
			System.out.println("Sales Taxes : " + totalTaxes);
			System.out.println("Total : " + total);
			
		}
		
	}

}
