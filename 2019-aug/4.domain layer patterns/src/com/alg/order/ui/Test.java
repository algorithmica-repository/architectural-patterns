package com.alg.order.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.alg.order.repository.IngredientRepository;
import com.alg.order.repository.OrderRepository;
import com.alg.order.repository.ProductRepository;
import com.alg.order.service.OrderServiceImpl;

public class Test {

	public static void main(String[] args) {
		JFrame frame;
		frame = new JFrame("Simple Ordering Application");
		ProductRepository prepository = new ProductRepository();
		IngredientRepository irepository = new IngredientRepository();
		OrderRepository orepository = new OrderRepository();
		frame.setContentPane(new ContentPanel(new OrderServiceImpl(prepository, irepository,
						orepository)));
		frame.pack();
		Dimension frameDim = new Dimension(500, 500);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frameDim.width / 2,
				screenSize.height / 2 - frameDim.height / 2);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(frameDim);
		frame.setVisible(true);
	}
}
