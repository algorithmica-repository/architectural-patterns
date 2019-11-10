package com.alg.concurrency.threading.wordcount1.sequential;

import java.util.HashMap;

public class Test {
	private static HashMap<String, Integer> counts = new HashMap<String, Integer>();

	private static void countWord(String word) {
		Integer currentCount = counts.get(word);
		if (currentCount == null)
			counts.put(word, 1);
		else
			counts.put(word, currentCount + 1);
	}

	private static void parseAndCountWords(int maxPages, String file) {
		Iterable<Page> pages = new Pages(maxPages, file);
		for (Page page : pages) {
			Iterable<String> words = new Words(page.getText());
			for (String word : words)
				countWord(word);
		}
	}

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		parseAndCountWords(Integer.parseInt(args[0]), args[1]);
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) / 1000.0 + "s");

		// for (Map.Entry<String, Integer> e : counts.entrySet()) {
		// System.out.println(e);
		// }
	}

}
