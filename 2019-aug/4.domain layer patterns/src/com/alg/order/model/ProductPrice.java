package com.alg.order.model;

import com.alg.order.common.AssertionConcern;
import com.alg.order.common.ValueObject;

public class ProductPrice extends AssertionConcern implements ValueObject {
	private Float price;

	public ProductPrice(Float price) {
		super();

		this.setPrice(price);
	}

	private void setPrice(Float aPrice) {
		this.assertArgumentNotNull(aPrice, "Product price is required.");

		this.price = aPrice;
	}

	public Float price() {
		return price;
	}
}
