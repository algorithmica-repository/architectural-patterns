package com.alg.order.model;

import com.alg.order.db.Entity;

public class Ingredient implements Item, Entity {
	private Integer id;
	private String name;
	private Float price;
	private String color;

	public Ingredient() {

	}

	public Ingredient(Integer id, String name, Float price, String color) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.color = color;
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

	public String description() {
		return getName();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
