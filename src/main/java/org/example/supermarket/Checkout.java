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
			Entry<Product, Integer> prdMap = scanIterator.next();
			rule.forEach(rule -> {
				if (rule.getProduct_name().equals(prdMap.getKey().getName())) {
					// int quantity = prdMap.getValue()/rule.getProduct_quantity();
					productTotal.put(prdMap.getKey(), prdMap.getValue() * rule.getProduct_price());
				}
			});
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
		if (scannedItems.get(items) != null)
			scannedItems.put(items, 1);
		else {
			int count = scannedItems.get(items);
			scannedItems.put(items, ++count);
		}
	}
}
