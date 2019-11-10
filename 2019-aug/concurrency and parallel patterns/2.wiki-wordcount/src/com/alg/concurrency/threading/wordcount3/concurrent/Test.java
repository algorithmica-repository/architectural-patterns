package com.alg.concurrency.threading.wordcount3.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws Exception {
		int NUM_COUNTERS = 2;
		ArrayBlockingQueue<Page> queue = new ArrayBlockingQueue<Page>(1000);
		Map<String, Integer> counts = new HashMap<String, Integer>();

		Thread parser = new Thread(new Parser(queue, Integer.parseInt(args[0]), args[1]));
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < NUM_COUNTERS; ++i)
			executor.execute(new WordCounter(queue, counts));

		long start = System.currentTimeMillis();
		parser.start();
		parser.join();
		for (int i = 0; i < NUM_COUNTERS; ++i)
			queue.put(new Page("terminate", null));
		executor.shutdown();
	    executor.awaitTermination(10L, TimeUnit.MINUTES);
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) / 1000.0 + "s");

		// for (Map.Entry<String, Integer> e: counts.entrySet()) {
		// System.out.println(e);
		// }
	}

}
