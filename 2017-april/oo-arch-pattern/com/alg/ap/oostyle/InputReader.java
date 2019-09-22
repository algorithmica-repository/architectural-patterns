package com.alg.ap.oostyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
	private List<String> lines = new ArrayList<String>();
	
	public void readInput(String infile) throws Exception {
		BufferedReader br = new BufferedReader(new BufferedReader(
				new FileReader(infile)));
		String line;
		while ((line = br.readLine()) != null)
			lines.add(line);
		br.close();
	}
	
	//TODO: replace it with iterator pattern
	public List<String> getLines() {
		return lines;
	}
}
