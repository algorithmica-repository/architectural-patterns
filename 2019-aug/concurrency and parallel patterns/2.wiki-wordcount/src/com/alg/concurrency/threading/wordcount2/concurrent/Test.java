package com.alg.concurrency.threading.wordcount2.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class Test {

	public static void main(String[] args) throws Exception {
		ArrayBlockingQueue<Page> queue = new ArrayBlockingQueue<Page>(1000);
		Map<String, Integer> counts = new HashMap<String, Integer>();

		Thread counter = new Thread(new WordCounter(queue, counts));
		Thread parser = new Thread(new Parser(queue, Integer.parseInt(args[0]), args[1]));
		long start = System.currentTimeMillis();
		counter.start();
		parser.start();
		parser.join();
		queue.put(new Page("terminate", null));
		counter.join();
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) / 1000.0 + "s");

		// for (Map.Entry<String, Integer> e: counts.entrySet()) {
		// System.out.println(e);
		// }
	}

}
