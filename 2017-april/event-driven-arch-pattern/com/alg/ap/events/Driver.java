package com.alg.ap.events;

public class Driver {

	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		InputReader ir = new InputReader(eventManager, args[0], "ir");
		CircularShifter shifter = new CircularShifter(eventManager, "cs");
		Sorter sorter = new Sorter(eventManager, "sorter");
		OutputWriter ow = new OutputWriter(eventManager, args[1],"ow");
		ir.start();
		shifter.start();
		sorter.start();
		ow.start();
	}
}
