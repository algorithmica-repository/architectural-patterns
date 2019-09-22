package com.alg.order.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alg.order.model.Ingredient;
import com.alg.order.model.IngredientColor;
import com.alg.order.model.IngredientId;
import com.alg.order.model.IngredientPrice;
import com.alg.order.repository.specifications.ISqlSpecification;

public class IngredientRepository extends AbstractRepository<Ingredient> {

	@Override
	public void save(Ingredient entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("insert into INGREDIENTS "
						+ "(INGREDIENT_ID, INGREDIENT_NAME, PRICE, COLOR)"
						+ "values (?, ?, ?, ?)");
		pstmt.setString(0, entity.getId().id());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice().price());
		pstmt.setString(3, entity.getColor().color());
		pstmt.execute();
		pstmt.close();
		close();
	}

	@Override
	public boolean delete(Ingredient entity) throws Exception {
		connect();
		PreparedStatement pstmt = connection
				.prepareStatement("DELETE FROM INGREDIENTS WHERE PRODUCT_ID = ?");
		pstmt.setString(0, entity.getId().id());
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
			Ingredient p = new Ingredient(new IngredientId(
					rs.getString("INGREDIENT_ID")),
					rs.getString("INGREDIENT_NAME"), new IngredientPrice(
							rs.getFloat("PRICE")), new IngredientColor(
							rs.getString("COLOR")));
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
		pstmt.setString(0, entity.getId().id());
		pstmt.setString(1, entity.getName());
		pstmt.setFloat(2, entity.getPrice().price());
		pstmt.setString(3, entity.getColor().color());
		boolean res = pstmt.executeUpdate() > 0;
		pstmt.close();
		close();
		return res;
	}

	@Override
	public List<Ingredient> query(ISqlSpecification specification)
			throws Exception {
		ArrayList<Ingredient> all = new ArrayList<Ingredient>();
		connect();
		PreparedStatement pstmt = connection.prepareStatement(specification
				.toSqlClauses());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Ingredient p = new Ingredient(new IngredientId(
					rs.getString("INGREDIENT_ID")),
					rs.getString("INGREDIENT_NAME"), new IngredientPrice(
							rs.getFloat("PRICE")), new IngredientColor(
							rs.getString("COLOR")));
			all.add(p);
		}
		rs.close();
		pstmt.close();
		close();
		return all;
	}

}
