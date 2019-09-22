package com.alg.order.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.model.EmailAddress;
import com.alg.order.model.IngredientId;
import com.alg.order.model.Order;
import com.alg.order.model.OrderId;
import com.alg.order.model.ProductId;
import com.alg.order.model.Telephone;
import com.alg.order.repository.specifications.ISqlSpecification;

public class OrderRepository extends AbstractRepository<Order> {

	@Override
	public void save(Order entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("INSERT INTO ORDERS "
						+ "(ORDER_ID, PRICE, PRODUCT_ID, INGREDIENT_ID, EMAIL, TEL)"
						+ "values (?, ?, ?, ?, ?, ?)");
		pstmt.setString(1, entity.getId().id());
		pstmt.setFloat(2, entity.getPrice());
		pstmt.setString(3, entity.productId().id());
		pstmt.setString(4, entity.ingredientId().id());
		pstmt.setString(5, entity.emailAddress().address());
		pstmt.setString(6, entity.telephone().number());
		pstmt.execute();
		pstmt.close();
		connection.close();
	}

	@Override
	public boolean delete(Order entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID = ?");
		pstmt.setString(0, entity.getId().id());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;
	}

	@Override
	public List<Order> findAll() throws Exception {
		ArrayList<Order> all = new ArrayList<Order>();
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM ORDERS ORDER BY ORDER_ID DESC");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Order o = new Order(new OrderId(rs.getString("ORDER_ID")),
					rs.getFloat("PRICE"), new ProductId(
							rs.getString("PRODUCT_ID")), new IngredientId(
							rs.getString("INGREDIENT_ID")), new EmailAddress(
							rs.getString("EMAIL")), new Telephone(
							rs.getString("TEL")));
			all.add(o);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

	@Override
	public boolean update(Order entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("UPDATE ORDERS SET ORDER_ID = ?, PRICE = ?)");
		pstmt.setString(0, entity.getId().id());
		pstmt.setFloat(1, entity.getPrice());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;

	}

	@Override
	public List<Order> query(ISqlSpecification specification) throws Exception {
		ArrayList<Order> all = new ArrayList<Order>();
		connect();
		PreparedStatement pstmt = connection.prepareStatement(specification
				.toSqlClauses());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Order o = new Order(new OrderId(rs.getString("ORDER_ID")),
					rs.getFloat("PRICE"), new ProductId(
							rs.getString("PRODUCT_ID")), new IngredientId(
							rs.getString("INGREDIENT_ID")), new EmailAddress(
							rs.getString("EMAIL")), new Telephone(
							rs.getString("TEL")));
			all.add(o);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

}
