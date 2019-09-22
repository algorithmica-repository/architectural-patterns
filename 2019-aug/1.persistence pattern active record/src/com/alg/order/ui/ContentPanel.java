package com.alg.order.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.alg.order.model.Ingredient;
import com.alg.order.model.Order;
import com.alg.order.model.Product;

public class ContentPanel extends Panel {
	private Panel leftPanel;
	private Choice selector;
	private Choice ingredients;
	private ArrayList<Product> allProducts = null;
	private ArrayList<Ingredient> allIngredients = null;
	private Order order;
	private JLabel price;
	private JLabel description;
	DefaultListModel ordersListModel = new DefaultListModel();
	JList orderList = new JList(ordersListModel);

	public ContentPanel() {
		super();
		order = new Order();
		setUI();

	}

	private void setUI() {
		this.setLayout(new BorderLayout());
		add(new Label("Brewing Station", Label.CENTER), BorderLayout.NORTH);
		buildLeftPanel();
		buildSouthPanel();
	}

	private void buildLeftPanel() {
		leftPanel = new Panel();
		GridLayout bl = new GridLayout(7, 1, 0, 0);
		leftPanel.setLayout(bl);
		selector = new Choice();
		try {
			allProducts = Product.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		selector.add("");
		for (Product p : allProducts) {
			selector.add(p.getName());
		}
		leftPanel.add(new Label("Chosee a drink:"));
		leftPanel.add(selector);

		ingredients = new Choice();
		try {
			allIngredients = Ingredient.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ingredients.add("");
		for (Ingredient i : allIngredients) {
			ingredients.add(i.getName());
		}

		leftPanel.add(new Label("Ingredients"));
		leftPanel.add(ingredients);

		price = new JLabel();
		leftPanel.add(price);
		description = new JLabel();
		description.setSize(500, 300);
		leftPanel.add(description);
		add(leftPanel, BorderLayout.CENTER);
		selector.addItemListener(new ProductSelectorListener());
		ingredients.addItemListener(new IngredientSelectorListener());
	}

	private void buildSouthPanel() {
		Panel southPanel = new Panel();
		southPanel.setLayout(new BorderLayout());
		Button brewBtn = new Button("Prepare");
		southPanel.add(brewBtn, BorderLayout.NORTH);

		orderList.setLayoutOrientation(JList.VERTICAL);
		orderList.setVisibleRowCount(500);

		JScrollPane listScroller = new JScrollPane(orderList);
		listScroller.setPreferredSize(new Dimension(250, 100));
		southPanel.add(listScroller, BorderLayout.SOUTH);
		add(southPanel, BorderLayout.SOUTH);
		brewBtn.addActionListener(new BrewActionListener());

		ArrayList<Order> orders;
		try {
			orders = Order.findAll();
			for (Order i : orders) {
				ordersListModel.addElement(i.id + " " + i.description + " "
						+ i.price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Product selectior listener
	class ProductSelectorListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			int selection = selector.getSelectedIndex();
			Product p = allProducts.get(selection - 1);
			order = new Order();
			order.decorate(p);
			price.setText("Total:" + order.getPrice().toString());
			description.setText(order.description());
		}
	}

	class IngredientSelectorListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			int selection = ingredients.getSelectedIndex();
			if (selection == 0)
				return;
			Ingredient p = allIngredients.get(selection - 1);
			order.decorate(p);
			price.setText("Total:" + order.getPrice().toString());
			description.setText(order.description());
		}
	}

	class BrewActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (order == null)
				return;
			try {
				order.save();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ordersListModel.clear();
			ArrayList<Order> orders = null;
			try {
				orders = Order.findAll();
				for (Order i : orders) {
					ordersListModel.addElement(i.id + " " + i.description + " "
							+ i.price);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			price.setText("Order placed: " + orders.get(0).id);
			description.setText("");
		}
	}

}
