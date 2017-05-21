package com.alg.ap.oostyle;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter {
	List<String> shifted_lines = new ArrayList<String>();
	
	public void circularShift(List<String> lines) {
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
	
	public List<String> getShiftedLines() {
		return shifted_lines;
	}
}
