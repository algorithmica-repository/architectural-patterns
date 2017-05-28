package com.alg.ap.pipefilter;

import java.io.*;

public class InputReaderFilter extends Filter {
	private String infile;

	public InputReaderFilter(String infile) {
		this.infile = infile;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new BufferedReader(
					new FileReader(infile)));
			String line;
			PrintStream ps = new PrintStream(getOutputWriterPort());
			System.out.println("Input Reader filter started");
			while ((line = br.readLine()) != null) {
				System.out.println("Input reader Filter: " + line);
				ps.println(line);
				//ps.flush();
			}
			closePorts();
			br.close();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
			closePorts();
		}
	}

}