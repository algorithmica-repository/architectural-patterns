package com.alg.order.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.db.specifications.ISqlSpecification;
import com.alg.order.model.Product;

public class ProductRepository extends AbstractRepository<Product> {

	@Override
	public void save(Product entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("INSERT INTO PRODUCTS "
						+ "(PRODUCT_ID, PRODUCT_NAME, PRICE)"
						+ "values (?, ?, ?)");
		pstmt.setInt(0, entity.getId());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice());
		pstmt.execute();
		pstmt.close();
		close();
	}

	@Override
	public boolean delete(Product entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?");
		pstmt.setInt(0, entity.getId());
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
			Product p = new Product();
			p.setId(rs.getInt("PRODUCT_ID"));
			p.setName(rs.getString("PRODUCT_NAME"));
			p.setPrice(rs.getFloat("PRICE"));
			all.add(p);
		}
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
		pstmt.setInt(0, entity.getId());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice());
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
		PreparedStatement pstmt = connection
				.prepareStatement(specification.toSqlClauses());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("PRODUCT_ID"));
			p.setName(rs.getString("PRODUCT_NAME"));
			p.setPrice(rs.getFloat("PRICE"));
			all.add(p);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

}
