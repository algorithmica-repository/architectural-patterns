package com.alg.order.repository.specifications;

import com.alg.order.model.ProductId;

public class ProductSpecificationById implements ISqlSpecification {
	private ProductId id;

	public ProductSpecificationById(ProductId id) {
		super();
		this.id = id;
	}

	@Override
	public String toSqlClauses() {
		return String.format("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = %s", id.id());
	}

}
