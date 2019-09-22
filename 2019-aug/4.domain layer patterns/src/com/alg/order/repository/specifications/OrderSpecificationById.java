package com.alg.order.repository.specifications;

import com.alg.order.model.OrderId;

public class OrderSpecificationById implements ISqlSpecification {
	private OrderId id;
	
	public OrderSpecificationById(OrderId id) {
		super();
		this.id = id;
	}

	@Override
	public String toSqlClauses() {
		return String.format("SELECT * FROM ORDERS WHERE ORDER_ID = %s", id.id());
	}

}
