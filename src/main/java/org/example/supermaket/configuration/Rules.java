package org.example.supermaket.configuration;

public class Rules {

	private String product_name;
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	private double product_price;
	private int product_quantity;
	
	Rules(String name, double price, int quantity){
		this.product_name = name;
		this.product_price = price;
		this.product_quantity = quantity;		
	}
}
