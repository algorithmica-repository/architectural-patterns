package com.alg.ap.oostyle;

public class Driver {

	public static void main(String[] args) throws Exception {
		InputReader ir = new InputReader();
		ir.readInput(args[0]);
		CircularShifter shifter = new CircularShifter();
		shifter.circularShift(ir.getLines());
		Sorter sorter = new Sorter();
		sorter.sort(shifter.getShiftedLines());
		OutputWriter ow = new OutputWriter();
		ow.writeOuput(sorter.getSortedShiftedLines(), args[1]);
	}

}
