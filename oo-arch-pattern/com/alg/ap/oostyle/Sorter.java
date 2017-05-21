package com.alg.ap.oostyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {
	private List<String> sorted_shifted_lines;

	public void sort(List<String> shifted_lines) {
		sorted_shifted_lines = new ArrayList<String>(shifted_lines);
		Collections.sort(sorted_shifted_lines);
	}
	
	public List<String> getSortedShiftedLines() {
		return sorted_shifted_lines;
	}
}
