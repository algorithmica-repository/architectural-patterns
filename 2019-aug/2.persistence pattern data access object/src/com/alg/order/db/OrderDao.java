package com.alg.order.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.model.Order;

public class OrderDao extends AbstractDao<Order> {

	@Override
	public Order find(Integer id) throws Exception {
		Order order = null;
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM ORDERS WHERE ORDER_ID = ? ");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			new Order(rs.getInt("ORDER_ID"), rs.getFloat("PRICE"),
					rs.getString("DESCRIPTION"));
		}
		rs.close();
		pstmt.close();
		close();
		return order;
	}

	@Override
	public void save(Order entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("INSERT INTO ORDERS "
						+ "(DESCRIPTION, PRICE)" + "values (?, ?)");
		pstmt.setString(1, entity.getDescription());
		pstmt.setFloat(2, entity.getPrice());
		pstmt.execute();
		pstmt.close();
		connection.close();
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID = ?");
		pstmt.setInt(0, id);
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
			Order p = new Order();
			p.setId(rs.getInt("ORDER_ID"));
			p.setDescription(rs.getString("DESCRIPTION"));
			p.setPrice(rs.getFloat("PRICE"));
			all.add(p);
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
				.prepareStatement("UPDATE ORDERS SET ORDER_ID = ?, ORDEER_DESCRIPTION = ?, PRICE = ?)");
		pstmt.setInt(0, entity.getId());
		pstmt.setString(1, entity.getDescription());
		pstmt.setFloat(2, entity.getPrice());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;

	}

}
