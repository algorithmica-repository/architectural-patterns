package com.alg.ap.pipefilter;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.TreeSet;

public class SorterFilter extends Filter {
	private TreeSet<String> sorted_lines = new TreeSet<String>();	

	public void run() {
		try {
			PrintStream ps = new PrintStream(getOutputWriterPort());
			DataInputStream dis = new DataInputStream(getInputReadPort());
			String line;
			System.out.println("Sorter Filter started");
			while((line = dis.readLine()) !=  null) {
				System.out.println("Sorter Filter:" + line);
				sorted_lines.add(line); 
			}
			for(String line1: sorted_lines)
				ps.println(line1);
			closePorts();
			ps.close();
			dis.close();
		} catch (Exception e) {
			System.out.println(e);
			closePorts();
		}
	}
}
