package com.alg.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alg.order.model.EmailAddress;
import com.alg.order.model.Ingredient;
import com.alg.order.model.IngredientId;
import com.alg.order.model.Order;
import com.alg.order.model.OrderId;
import com.alg.order.model.Product;
import com.alg.order.model.ProductId;
import com.alg.order.model.Telephone;
import com.alg.order.repository.IRepository;
import com.alg.order.repository.specifications.IngredeintSpecificationById;
import com.alg.order.repository.specifications.OrderSpecificationById;
import com.alg.order.repository.specifications.ProductSpecificationById;

public class OrderServiceImpl implements IOrderService {
	private IRepository<Product> product_repository;
	private IRepository<Ingredient> ing_repository;
	private IRepository<Order> order_repository;

	public OrderServiceImpl(IRepository<Product> product_repository,
			IRepository<Ingredient> ing_repository,
			IRepository<Order> order_repository) {
		super();
		this.product_repository = product_repository;
		this.ing_repository = ing_repository;
		this.order_repository = order_repository;
	}

	@Override
	public OrderSummaryDTO getOrderSummary(OrderId order_id) {
		OrderSummaryDTO summary = null;
		try {
			List<Order> orders = order_repository
					.query(new OrderSpecificationById(order_id));
			ProductId pid = orders.get(0).productId();
			IngredientId iid = orders.get(0).ingredientId();
			List<Product> products = product_repository
					.query(new ProductSpecificationById(pid));
			List<Ingredient> ingredients = ing_repository
					.query(new IngredeintSpecificationById(iid));
			summary = new OrderSummaryDTO(order_id, products.get(0).getName(),
					ingredients.get(0).getName(), orders.get(0).getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summary;
	}

	@Override
	public List<OrderSummaryDTO> getAllOrderSummaries() {
		List<OrderSummaryDTO> summaries = new ArrayList<OrderSummaryDTO>();
		try {
			List<Order> orders = order_repository.findAll();
			for (Order order : orders) {
				ProductId pid = order.productId();
				IngredientId iid = order.ingredientId();
				List<Product> products = product_repository
						.query(new ProductSpecificationById(pid));
				List<Ingredient> ingredients = ing_repository
						.query(new IngredeintSpecificationById(iid));
				summaries.add(new OrderSummaryDTO(order.getId(), products
						.get(0).getName(), ingredients.get(0).getName(), orders
						.get(0).getPrice()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaries;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productdtos = new ArrayList<ProductDTO>();
		try {
			List<Product> products = product_repository.findAll();
			for (Product product : products) {
				productdtos.add(new ProductDTO(product.getName(), product
						.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productdtos;
	}

	@Override
	public List<IngredientDTO> getAllIngredients() {
		List<IngredientDTO> ingredientdtos = new ArrayList<IngredientDTO>();
		try {
			List<Ingredient> ingredients = ing_repository.findAll();
			for (Ingredient ingredient : ingredients) {
				ingredientdtos.add(new IngredientDTO(ingredient.getName(),
						ingredient.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientdtos;
	}

	@Override
	public void addOrder(OrderDTO orderdto) {
		ProductId pid = new ProductId(orderdto.getPid());
		IngredientId iid = new IngredientId(orderdto.getIid());
		OrderId oid = new OrderId(Integer.toString(new Random().nextInt(100)));
		float price = 0.0f;
		try {
			price += product_repository
					.query(new ProductSpecificationById(pid)).get(0).getPrice()
					.price();
			price += ing_repository.query(new IngredeintSpecificationById(iid))
					.get(0).getPrice().price();
			order_repository.save(new Order(oid, price, pid, iid,
					new EmailAddress(orderdto.getEmail()), new Telephone(
							orderdto.getTel())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
