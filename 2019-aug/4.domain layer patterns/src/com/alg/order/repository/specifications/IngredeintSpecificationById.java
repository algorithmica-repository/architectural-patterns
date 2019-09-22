package com.alg.order.repository.specifications;

import com.alg.order.model.IngredientId;

public class IngredeintSpecificationById implements ISqlSpecification {
	private IngredientId id;

	public IngredeintSpecificationById(IngredientId id) {
		super();
		this.id = id;
	}

	@Override
	public String toSqlClauses() {
		return String.format("SELECT * FROM INGREDIENTS WHERE INGREDIENT_ID = %s", id.id());
	}

}
