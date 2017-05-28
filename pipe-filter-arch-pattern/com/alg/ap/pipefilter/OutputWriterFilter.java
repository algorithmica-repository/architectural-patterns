package com.alg.ap.pipefilter;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;

public class OutputWriterFilter extends Filter {
	private String outfile;

	public OutputWriterFilter(String outfile) {
		this.outfile = outfile;
	}

	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
			DataInputStream dis = new DataInputStream(getInputReadPort());
			String line;
			System.out.println("Output writer filter started");
			while((line = dis.readLine()) != null) {
				System.out.println("Output writer filter:" + line);
				bw.write(line);
				bw.newLine();
			}
			closePorts();
			bw.close();
			dis.close();
		} catch (Exception e) {
			System.out.println(e);
			closePorts();
		}
	}

}
