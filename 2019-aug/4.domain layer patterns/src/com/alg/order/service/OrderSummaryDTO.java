package com.alg.order.service;

import com.alg.order.model.OrderId;

public class OrderSummaryDTO {
	private OrderId order_id;
	private String product_name;
	private String ingredient_name;
	private Float total_price;

	public OrderSummaryDTO(OrderId order_id, String product_name, String ingredient_name,
			Float total_price) {
		super();
		this.order_id = order_id;
		this.product_name = product_name;
		this.ingredient_name = ingredient_name;
		this.total_price = total_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getIngredient_name() {
		return ingredient_name;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public OrderId getOrder_id() {
		return order_id;
	}

}
