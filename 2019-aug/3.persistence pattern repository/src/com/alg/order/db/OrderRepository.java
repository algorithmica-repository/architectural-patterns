package com.alg.order.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.db.specifications.ISqlSpecification;
import com.alg.order.model.Order;

public class OrderRepository extends AbstractRepository<Order> {

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
	public boolean delete(Order entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID = ?");
		pstmt.setInt(0, entity.getId());
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

	@Override
	public List<Order> query(ISqlSpecification specification) throws Exception {
		ArrayList<Order> all = new ArrayList<Order>();
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM ORDERS ORDER BY ORDER_ID DESC " + specification.toSqlClauses());
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

}
