package com.alg.order.model;

import com.alg.order.common.Entity;

public class Product implements Entity {
	private ProductId id;
	private String name;
	private ProductPrice price;

	public Product() {

	}

	public Product(ProductId id, String name, ProductPrice price) {
		super();
		this.id = id;
		setName(name);
		this.price = price;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ProductId getId() {
		return id;
	}

	public ProductPrice getPrice() {
		return price;
	}

}
