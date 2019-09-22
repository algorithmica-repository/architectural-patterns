package com.alg.order.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Test {

	public static void main(String[] args) {
		JFrame frame;
		frame = new JFrame("Simple Ordering Application");
		frame.setContentPane(new ContentPanel());
		frame.pack();
		Dimension frameDim = new Dimension(400, 400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - frameDim.width / 2,
				screenSize.height / 2 - frameDim.height / 2);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(frameDim);
		frame.setVisible(true);
	}
}
