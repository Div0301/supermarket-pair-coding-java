package org.example.supermarket;

import com.google.common.collect.Lists;
import org.example.supermaket.configuration.Product;
import org.example.supermaket.configuration.Rules;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;



public class CheckoutTest {

	private static List<Rules> RULES = new ArrayList<>();

	@Before
	static void setup() {
		RULES = Lists.newArrayList(new Rules("A", 90, 1, true, 3, 250), new Rules("B", 10, 1, true, 2, 17));
	}

	Double price(List<Product> items) {
		Checkout co = new Checkout(RULES);
		for (int i = 0; i < items.size(); i++) {
			Product item = items.get(i);
			co.scan(item);
		}
		return co.total();
	}

	@Test
	void testTotals() {
		assertEquals(0.0, price(Lists.newArrayList(new Product(""))));
		assertEquals(90, price(Lists.newArrayList(new Product("A"))));
		assertEquals(100, price(Lists.newArrayList(new Product("A"), new Product("B"))));
		// assertEquals(190, price(Lists.newArrayList(new Product("C"), new
		// Product("D"), new Product("B"), new Product("A"))));

		assertEquals(180, price(Lists.newArrayList(new Product("A"), new Product("A"))));
		assertEquals(250, price(Lists.newArrayList(new Product("A"), new Product("A"), new Product("A"))));
		assertEquals(500, price(Lists.newArrayList(new Product("A"), new Product("A"), new Product("A"),
				new Product("A"), new Product("A"), new Product("A"))));

		assertEquals(260,
				price(Lists.newArrayList(new Product("A"), new Product("A"), new Product("A"), new Product("B"))));
		assertEquals(267, price(Lists.newArrayList(new Product("A"), new Product("A"), new Product("A"),
				new Product("B"), new Product("B"))));
	}

	@Test
	void testIncrementals() {
		Checkout co = new Checkout(RULES);
		assertEquals(0, co.total());
		co.scan(new Product("A"));
		assertEquals(90, co.total());
		co.scan(new Product("B"));
		assertEquals(100, co.total());
		co.scan(new Product("A"));
		assertEquals(190, co.total());
		co.scan(new Product("A"));
		assertEquals(260, co.total());
		co.scan(new Product("B"));
		assertEquals(267, co.total());
	}
}
