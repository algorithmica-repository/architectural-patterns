package com.alg.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alg.order.db.Base;

public class Product extends Base implements Item {
	private Integer id;
	private String name;
	private Float price;

	public Product() {

	}
	
	public Product(Integer id, String name, Float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public void find(Integer id) throws SQLException {
		this.connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ? ");
		pstmt.setInt(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			this.name = rs.getString("PRODUCT_NAME");
			this.price = rs.getFloat("PRICE");
		}
		rs.close();
		pstmt.close();
		connection.close();
	}

	public static ArrayList<Product> findAll() throws SQLException {
		ArrayList<Product> all = new ArrayList<Product>();
		Product base = new Product();
		base.connect();
		PreparedStatement pstmt = base.connection
				.prepareStatement("SELECT * FROM PRODUCTS");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product p = new Product();
			p.id = rs.getInt("PRODUCT_ID");
			p.name = rs.getString("PRODUCT_NAME");
			p.price = rs.getFloat("PRICE");
			all.add(p);
		}
		rs.close();
		pstmt.close();
		base.connection.close();
		return all;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public String description() {
		return getName();
	}
}
