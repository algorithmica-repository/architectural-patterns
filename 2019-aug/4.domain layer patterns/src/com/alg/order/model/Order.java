package com.alg.order.model;

import com.alg.order.common.Entity;

public class Order implements Entity {
	private OrderId id;
	private Float price;
	private ProductId product_id;
	private IngredientId ingredient_id;
	private EmailAddress email_address;
	private PostalAddress postal_address;
	private Telephone telephone;

	public Order(OrderId id, Float price, ProductId product_id,
			IngredientId ingredient_id, EmailAddress email_address,
			Telephone telephone) {
		this.id = id;
		this.price = price;
		this.product_id = product_id;
		this.ingredient_id = ingredient_id;
		this.email_address = email_address;
		this.telephone = telephone;
	}

	public Float getPrice() {
		return price;
	}

	public OrderId getId() {
		return id;
	}

	public ProductId productId() {
		return product_id;
	}

	public IngredientId ingredientId() {
		return ingredient_id;
	}

	public EmailAddress emailAddress() {
		return email_address;
	}

	public Telephone telephone() {
		return telephone;
	}

}
