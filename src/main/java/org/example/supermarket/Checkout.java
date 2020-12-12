package org.example.supermarket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.example.supermaket.configuration.Product;
import org.example.supermaket.configuration.Rules;

public class Checkout {

	HashMap<Product, Integer> scannedItems = new HashMap<>();

	HashMap<Product, Double> productTotal = new HashMap<>();

	private List<Rules> rule;

	public Checkout(List<Rules> rules) {
		this.rule = rules;

	}

	public Double total() {

		Iterator<Entry<Product, Integer>> scanIterator = scannedItems.entrySet().iterator();
		while (scanIterator.hasNext()) {
			
			double totalSpecialPrice = 0;
			Entry<Product, Integer> prdMap = scanIterator.next();
			int itemLeft = prdMap.getValue();
			Iterator<Rules> itr = rule.iterator();
			
			while (itr.hasNext()) {
				Rules nextRule = itr.next();
				if (nextRule.getProduct_name().equals(prdMap.getKey().getName())) {

					if (nextRule.isSpecialPrice()) {
						if (prdMap.getValue() >= nextRule.getSpecialPrtQuantity()) {
							int quantity = prdMap.getValue() / nextRule.getSpecialPrtQuantity();
							totalSpecialPrice = quantity * nextRule.getSpecialPrtPrice();
							itemLeft = prdMap.getValue() - quantity * nextRule.getSpecialPrtQuantity();
						}
					}
					totalSpecialPrice = totalSpecialPrice + itemLeft*nextRule.getProduct_price();
					productTotal.put(prdMap.getKey(), totalSpecialPrice);
					break;
				}
			}
		}

		double total = 0;
		Iterator<Entry<Product, Double>> iterator = productTotal.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Product, Double> map = iterator.next();
			total = total + map.getValue();
		}
		return total;
	}

	public void scan(Product items) {
		if (scannedItems.get(items) == null)
			scannedItems.put(items, 1);
		else {
			int count = scannedItems.get(items);
			scannedItems.put(items, ++count);
		}
	}
}
