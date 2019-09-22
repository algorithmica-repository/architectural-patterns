package com.alg.order.service;

import com.alg.order.model.IngredientId;

public class IngredientDTO {
	private String name;
	private IngredientId id;

	public IngredientDTO(String name, IngredientId id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IngredientId getId() {
		return id;
	}

	public void setId(IngredientId id) {
		this.id = id;
	}

}
