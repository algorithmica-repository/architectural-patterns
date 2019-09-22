package com.alg.order.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.model.Ingredient;

public class IngredientDao extends AbstractDao<Ingredient> {

	@Override
	public Ingredient find(Integer id) throws Exception {
		Ingredient ingredient = null;
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM INGREDIENTS WHERE INGREDIENT_ID = ? ");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			new Ingredient(rs.getInt("INGREDIENT_ID"),
					rs.getString("INGREDIENT_NAME"), rs.getFloat("PRICE"),
					rs.getString("COLOR"));
		}
		rs.close();
		pstmt.close();
		close();
		return ingredient;
	}

	@Override
	public void save(Ingredient entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("insert into INGREDIENTS "
						+ "(INGREDIENT_ID, INGREDIENT_NAME, PRICE, COLOR)"
						+ "values (?, ?, ?, ?)");
		pstmt.setInt(0, entity.getId());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice());
		pstmt.setString(3, entity.getColor());
		pstmt.execute();
		pstmt.close();
		close();
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM INGREDIENTS WHERE PRODUCT_ID = ?");
		pstmt.setInt(0, id);
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;
	}

	@Override
	public List<Ingredient> findAll() throws Exception {
		ArrayList<Ingredient> all = new ArrayList<Ingredient>();
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM INGREDIENTS");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Ingredient p = new Ingredient();
			p.setId(rs.getInt("INGREDIENT_ID"));
			p.setName(rs.getString("INGREDIENT_NAME"));
			p.setPrice(rs.getFloat("PRICE"));
			p.setColor(rs.getString("COLOR"));
			all.add(p);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

	@Override
	public boolean update(Ingredient entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("UPDATE INGREDIENTS SET INGREDIENT_ID = ?, INGREDIENT_NAME = ?, PRICE = ?, COLOR=?)");
		pstmt.setInt(0, entity.getId());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice());
		pstmt.setString(3, entity.getColor());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;
	}

}
