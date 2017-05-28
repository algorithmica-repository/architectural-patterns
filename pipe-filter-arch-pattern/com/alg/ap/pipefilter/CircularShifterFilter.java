package com.alg.ap.pipefilter;

import java.io.DataInputStream;
import java.io.PrintStream;

public class CircularShifterFilter extends Filter {

	public void run() {
		try {
			PrintStream ps = new PrintStream(getOutputWriterPort());
			DataInputStream dis = new DataInputStream(getInputReadPort());
			String line;
			System.out.println("Circular Shifter Filter Started");
			while((line = dis.readLine()) != null) {	
				int nshifts = line.split(" ").length;
				for (int i = 0; i < nshifts; ++i) {
					int pos = line.indexOf(' ');
					String current = line.substring(0, pos);
					String tmp = line.substring(pos + 1) + " " + current;
					ps.println(tmp);
					System.out.println("Circular Shifter Filter:" + tmp);
					line = tmp;
				}
			}
			closePorts();
			ps.close();
			dis.close();
		} catch (Exception e) {
			System.out.println(e);
			closePorts();
		}
	}

}
