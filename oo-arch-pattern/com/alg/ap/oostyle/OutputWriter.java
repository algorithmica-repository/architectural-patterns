package com.alg.ap.oostyle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class OutputWriter {

	public void writeOuput(List<String> sorted_shifted_lines, String outfile)
			throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
		for (String line : sorted_shifted_lines) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}
}
