package com.alg.concurrency.threading.wordcount3.concurrent;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

class WordCounter implements Runnable {
	private BlockingQueue<Page> queue;
	private Map<String, Integer> counts;
	private ReentrantLock lock;

	public WordCounter(BlockingQueue<Page> queue, Map<String, Integer> counts) {
		this.queue = queue;
		this.counts = counts;
		this.lock = new ReentrantLock();
	}

	public void run() {
		try {
			while (true) {
				Page page = queue.take();
				// System.out.println("counter:" +page.getTitle());
				if (page.getTitle().equals("terminate"))
					break;
				Iterable<String> words = new Words(page.getText());
				for (String word : words)
					countWord(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void countWord(String word) {
		lock.lock();
		try {
			Integer currentCount = counts.get(word);
			if (currentCount == null)
				counts.put(word, 1);
			else
				counts.put(word, currentCount + 1);
		} finally {
			lock.unlock();
		}

	}

}
