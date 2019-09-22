package com.alg.order.model;

import com.alg.order.common.Entity;

public class Ingredient implements Entity {
	private IngredientId id;
	private String name;
	private IngredientPrice price;
	private IngredientColor color;

	public Ingredient() {

	}

	public Ingredient(IngredientId id, String name, IngredientPrice price,
			IngredientColor color) {
		super();
		this.id = id;
		this.setName(name);
		this.price = price;
		this.color = color;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public IngredientId getId() {
		return id;
	}

	public void setId(IngredientId id) {
		this.id = id;
	}

	public IngredientPrice getPrice() {
		return price;
	}

	public IngredientColor getColor() {
		return color;
	}

}
