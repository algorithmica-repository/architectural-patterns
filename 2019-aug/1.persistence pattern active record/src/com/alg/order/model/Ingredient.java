package com.alg.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alg.order.db.Base;

public class Ingredient extends Base implements Item {
	private Integer id;
	private String name;
	private Float price;
	private String color;

	public Ingredient() {
		
	}
	public Ingredient(Integer id, String name, Float price, String color) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.color = color;
	}

	public void find(Integer id) throws SQLException {
		this.connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM INGREDIENTS WHERE INGREDIENT_ID = ? ");
		pstmt.setInt(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			this.name = rs.getString("INGREDIENT_NAME");
			this.price = rs.getFloat("PRICE");
			this.color = rs.getString("COLOR");
		}
		rs.close();
		pstmt.close();
		connection.close();
	}

	public static ArrayList<Ingredient> findAll() throws SQLException {
		ArrayList<Ingredient> all = new ArrayList<Ingredient>();
		Ingredient base = new Ingredient();
		base.connect();
		PreparedStatement pstmt = base.connection
				.prepareStatement("SELECT * FROM INGREDIENTS");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Ingredient p = new Ingredient();
			p.id = rs.getInt("INGREDIENT_ID");
			p.name = rs.getString("INGREDIENT_NAME");
			p.price = rs.getFloat("PRICE");
			p.color = rs.getString("COLOR");
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
