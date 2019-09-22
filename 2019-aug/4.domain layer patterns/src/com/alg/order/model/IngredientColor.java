package com.alg.order.model;

import com.alg.order.common.AssertionConcern;
import com.alg.order.common.ValueObject;

public class IngredientColor extends AssertionConcern implements ValueObject {
	private String color;

	public IngredientColor(String color) {
		super();

		this.setColor(color);
	}

	private void setColor(String aColor) {
		this.assertArgumentNotEmpty(aColor, "The color is required.");

		this.color = aColor;
	}

	public String color() {
		return color;
	}

}
