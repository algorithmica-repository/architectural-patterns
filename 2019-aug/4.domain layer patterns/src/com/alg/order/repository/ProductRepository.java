package com.alg.order.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.model.Product;
import com.alg.order.model.ProductId;
import com.alg.order.model.ProductPrice;
import com.alg.order.repository.specifications.ISqlSpecification;

public class ProductRepository extends AbstractRepository<Product> {

	@Override
	public void save(Product entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("INSERT INTO PRODUCTS "
						+ "(PRODUCT_ID, PRODUCT_NAME, PRICE)"
						+ "values (?, ?, ?)");
		pstmt.setString(0, entity.getId().id());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice().price());
		pstmt.execute();
		pstmt.close();
		close();
	}

	@Override
	public boolean delete(Product entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?");
		pstmt.setString(0, entity.getId().id());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;

	}

	@Override
	public List<Product> findAll() throws Exception {
		ArrayList<Product> all = new ArrayList<Product>();
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM PRODUCTS");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product p = new Product(new ProductId(rs.getString("PRODUCT_ID")),
					rs.getString("PRODUCT_NAME"), new ProductPrice(
							rs.getFloat("PRICE")));
			all.add(p);
		}
		//System.out.println(all);
		rs.close();
		pstmt.close();
		close();
		return all;
	}

	@Override
	public boolean update(Product entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("UPDATE PRODUCTS SET PRODUCT_ID = ?, PRODUCT_NAME = ?, PRICE = ?)");
		pstmt.setString(0, entity.getId().id());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice().price());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;

	}

	@Override
	public List<Product> query(ISqlSpecification specification)
			throws Exception {
		ArrayList<Product> all = new ArrayList<Product>();
		connect();
		PreparedStatement pstmt = connection.prepareStatement(specification
				.toSqlClauses());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product p = new Product(new ProductId(rs.getString("PRODUCT_ID")),
					rs.getString("PRODUCT_NAME"), new ProductPrice(
							rs.getFloat("PRICE")));
			all.add(p);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

}
