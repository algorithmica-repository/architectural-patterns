package com.alg.order.service;

import com.alg.order.model.ProductId;

public class ProductDTO {
	private String name;
	private ProductId id;

	public ProductDTO(String name, ProductId id) {
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

	public ProductId getId() {
		return id;
	}

	public void setId(ProductId id) {
		this.id = id;
	}

}
