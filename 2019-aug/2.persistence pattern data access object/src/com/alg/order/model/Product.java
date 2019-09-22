package com.alg.order.model;

import com.alg.order.db.Entity;

public class Product implements Item, Entity {
	private Integer id;
	private String name;
	private Float price;

	public Product() {

	}

	public Product(Integer id, String name, Float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String description() {
		return getName();
	}
}
