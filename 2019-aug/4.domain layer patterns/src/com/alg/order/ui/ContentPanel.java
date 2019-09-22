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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.alg.order.service.IOrderService;
import com.alg.order.service.IngredientDTO;
import com.alg.order.service.OrderDTO;
import com.alg.order.service.OrderSummaryDTO;
import com.alg.order.service.ProductDTO;

public class ContentPanel extends Panel {
	private Panel leftPanel;
	private Choice pselector;
	private Choice iselector;
	private OrderDTO odto;
	private DefaultListModel ordersListModel = new DefaultListModel();
	private JList orderList = new JList(ordersListModel);
	private JTextField email;
	private JTextField tel;
	private IOrderService oservice;

	public ContentPanel(IOrderService oservice) {
		super();
		this.oservice = oservice;
		odto = new OrderDTO();
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

		pselector = new Choice();
		List<ProductDTO> allProducts = oservice.getAllProducts();
		pselector.add("");
		for (ProductDTO p : allProducts) {
			pselector.add(p.getId().id());
		}
		pselector.addItemListener(new ProductSelectorListener());
		leftPanel.add(new Label("Chosee a drink:"));
		leftPanel.add(pselector);

		iselector = new Choice();
		List<IngredientDTO> allIngredients = oservice.getAllIngredients();
		iselector.add("");
		for (IngredientDTO i : allIngredients) {
			iselector.add(i.getId().id());
		}
		iselector.addItemListener(new IngredientSelectorListener());
		leftPanel.add(new Label("Ingredients"));
		leftPanel.add(iselector);

		email = new JTextField();
		leftPanel.add(new Label("Email"));
		leftPanel.add(email);

		tel = new JTextField();
		leftPanel.add(new Label("Telephone"));
		leftPanel.add(tel);

		add(leftPanel, BorderLayout.CENTER);
	}

	private void buildSouthPanel() {
		Panel southPanel = new Panel();
		southPanel.setLayout(new BorderLayout());
		Button brewBtn = new Button("Prepare");
		southPanel.add(brewBtn, BorderLayout.NORTH);
		
		List<OrderSummaryDTO> odtos = oservice.getAllOrderSummaries();
		for (OrderSummaryDTO i : odtos) {
			ordersListModel.addElement(i.getOrder_id() + " "
					+ i.getProduct_name() + " " + i.getIngredient_name()
					+ " " + i.getTotal_price());
		}
		orderList.setLayoutOrientation(JList.VERTICAL);
		orderList.setVisibleRowCount(500);

		JScrollPane listScroller = new JScrollPane(orderList);
		listScroller.setPreferredSize(new Dimension(250, 100));
		southPanel.add(listScroller, BorderLayout.SOUTH);
		add(southPanel, BorderLayout.SOUTH);
		brewBtn.addActionListener(new BrewActionListener());
	}

	class ProductSelectorListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			odto.setPid((String) e.getItem());
		}
	}

	class IngredientSelectorListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			odto.setIid((String) e.getItem());
		}
	}

	class BrewActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			odto.setEmail(email.getText());
			odto.setTel(tel.getText());
			oservice.addOrder(odto);
			ordersListModel.clear();
			List<OrderSummaryDTO> odtos = oservice.getAllOrderSummaries();
			for (OrderSummaryDTO i : odtos) {
				ordersListModel.addElement(i.getOrder_id() + " "
						+ i.getProduct_name() + " " + i.getIngredient_name()
						+ " " + i.getTotal_price());
			}
		}
	}

}
