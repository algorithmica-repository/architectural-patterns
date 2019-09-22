package com.alg.order.model;

import com.alg.order.db.Entity;

public class Order implements Entity {
	private Integer id;
	private Float price;
	private String description;

	public Order() {
		price = 0.0f;
		description = "Order with ";
	}

	public Order(Integer id, Float price, String description) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
	}

	public void decorate(Item item) {
		this.price = this.price + item.getPrice();
		this.description = this.description + " " + item.description();
	}

	public String description() {
		return description;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Float price) {
		this.price = price;
	}


}
