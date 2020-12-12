package org.example.supermaket.configuration;

public class Product {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product(String item) {
		this.name = item;
	}

}
