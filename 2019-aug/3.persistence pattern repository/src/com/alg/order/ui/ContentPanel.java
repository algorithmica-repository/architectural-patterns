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
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.alg.order.db.IngredientRepository;
import com.alg.order.db.OrderRepository;
import com.alg.order.db.ProductRepository;
import com.alg.order.model.Ingredient;
import com.alg.order.model.Order;
import com.alg.order.model.Product;

public class ContentPanel extends Panel {
	private Panel leftPanel;
	private Choice selector;
	private Choice ingredients;
	private List<Product> allProducts = null;
	private List<Ingredient> allIngredients = null;
	private Order order;
	private JLabel price;
	private JLabel description;
	DefaultListModel ordersListModel = new DefaultListModel();
	JList orderList = new JList(ordersListModel);
	private ProductRepository prepository;
	private IngredientRepository irepository;
	private OrderRepository orepository;

	public ContentPanel(ProductRepository prepository,
			IngredientRepository irepository, OrderRepository orepository) {
		super();
		this.prepository = prepository;
		this.irepository = irepository;
		this.orepository = orepository;
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
			allProducts = prepository.findAll();
		} catch (Exception e) {
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
			allIngredients = irepository.findAll();
		} catch (Exception e) {
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

		List<Order> orders;
		try {
			orders = orepository.findAll();
			for (Order i : orders) {
				ordersListModel.addElement(i.getId() + " " + i.getDescription()
						+ " " + i.getPrice());
			}
		} catch (Exception e) {
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
				orepository.save(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ordersListModel.clear();
			List<Order> orders = null;
			try {
				orders = orepository.findAll();
				for (Order i : orders) {
					ordersListModel.addElement(i.getId() + " "
							+ i.getDescription() + " " + i.getPrice());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			price.setText("Order placed: " + orders.get(0).getId());
			description.setText("");
		}
	}

}
