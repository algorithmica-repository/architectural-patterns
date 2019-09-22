package com.alg.ap.procedural;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {
	static List<String> lines = new ArrayList<String>();
	static List<String> shifted_lines = new ArrayList<String>();
		
	public static void readInput(String infile) throws Exception {
		BufferedReader br = new BufferedReader(new BufferedReader(
				new FileReader(infile)));
		String line;
		while ((line = br.readLine()) != null)
			lines.add(line);
		br.close();
	}

	public static void circularShift() {
		for (String line : lines) {
			int nshifts = line.split(" ").length;
			for (int i = 0; i < nshifts; ++i) {
				int pos = line.indexOf(' ');
				String current = line.substring(0, pos);
				String tmp = line.substring(pos+1) + " " + current;
				shifted_lines.add(tmp);
				line = tmp;
			}
		}
	}

	public static void sort() {
		Collections.sort(shifted_lines);
	}

	public static void writeOutput(String outfile) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
		for(String line: shifted_lines) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

	public static void main(String[] args) throws Exception {
		readInput(args[0]);
		System.out.println(lines);
		circularShift();
		System.out.println(shifted_lines);
		sort();
		System.out.println(shifted_lines);
		writeOutput(args[1]);
	}

}
