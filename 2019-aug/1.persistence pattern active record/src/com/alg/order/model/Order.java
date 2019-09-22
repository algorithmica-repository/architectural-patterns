package com.alg.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alg.order.db.Base;

public class Order extends Base implements Item {
	public Integer id;
	public Float price;
	public String description;

	public Order() {
		price = 0.0f;
		description = "Order with ";
	}

	public void find(Integer id) throws SQLException {
		this.connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID = ? ");
		pstmt.setInt(1, this.id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			this.price = rs.getFloat("PRICE");
			this.description = rs.getString("DESCRIPTION");
		}
		rs.close();
		pstmt.close();
		connection.close();
	}

	public void save() throws SQLException {
		this.connect();
		PreparedStatement pstmt = connection
				.prepareStatement("insert into ORDERS "
						+ "(DESCRIPTION, PRICE)" + "values (?, ?)");
		pstmt.setString(1, this.description);
		pstmt.setFloat(2, this.price);
		pstmt.execute();
		pstmt.close();
		connection.close();
	}

	public static ArrayList<Order> findAll() throws SQLException {
		ArrayList<Order> all = new ArrayList<Order>();
		Order base = new Order();
		base.connect();
		PreparedStatement pstmt = base.connection
				.prepareStatement("SELECT * FROM ORDERS ORDER BY ORDER_ID DESC");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Order p = new Order();
			p.id = rs.getInt("ORDER_ID");
			p.description = rs.getString("DESCRIPTION");
			p.price = rs.getFloat("PRICE");
			all.add(p);
		}
		rs.close();
		pstmt.close();
		base.connection.close();
		return all;
	}
	public void decorate(Item item) {
		this.price = this.price + item.getPrice();
		this.description = this.description + " " + item.description();
	}

	public String description() {
		return description;
	}

	public Float getPrice() {
		return price;
	}


}
