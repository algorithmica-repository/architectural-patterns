package com.alg.concurrency.threading.wordcount3.concurrent;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

class Parser implements Runnable {
	private BlockingQueue<Page> queue;
	private int maxPages;
	private String file;

	public Parser(BlockingQueue<Page> queue, int maxPages, String file) {
		this.queue = queue;
		this.maxPages = maxPages;
		this.file = file;
	}

	public void run() {
		try {
			Iterator<Page> pages = new Pages(maxPages, file).iterator();
			while(pages.hasNext()) {
				Page page = pages.next();
				queue.put(page);
				//System.out.println("parser:" + page.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}