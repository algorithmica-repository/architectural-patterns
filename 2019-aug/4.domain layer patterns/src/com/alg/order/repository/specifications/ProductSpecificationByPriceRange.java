package com.alg.order.repository.specifications;

public class ProductSpecificationByPriceRange implements ISqlSpecification {
	private float min_price;
	private float max_price;

	public ProductSpecificationByPriceRange(float min_price, float max_price) {
		super();
		this.min_price = min_price;
		this.max_price = max_price;
	}

	@Override
	public String toSqlClauses() {
		return String.format("SELECT * FROM PRODUCTS WHERE PRICE BETWEEN %s AND %s", min_price, max_price);
	}

}
