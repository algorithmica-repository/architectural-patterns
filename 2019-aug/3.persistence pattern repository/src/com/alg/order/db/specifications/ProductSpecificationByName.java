package com.alg.order.db.specifications;

public class ProductSpecificationByName implements ISqlSpecification {
	private String name;

	public ProductSpecificationByName(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toSqlClauses() {
		return String.format("SELECT * FROM PRODUCTS WHERE PRODUCT_NAME = %s", name);
	}

}
