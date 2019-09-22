package com.alg.order.service;

import java.util.List;

import com.alg.order.model.OrderId;

public interface IOrderService {
	List<OrderSummaryDTO> getAllOrderSummaries();

	OrderSummaryDTO getOrderSummary(OrderId order_id);

	List<ProductDTO> getAllProducts();

	List<IngredientDTO> getAllIngredients();
	
	void addOrder(OrderDTO orderdto);
}
