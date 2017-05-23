package com.alg.ap.events;

import java.util.TreeSet;

public class Sorter extends Thread {
	private TreeSet<String> sorted_lines;
	private EventManager eventManager;
	private String id;

	public Sorter(EventManager eventManager, String id) {
		sorted_lines = new TreeSet<String>();
		this.eventManager = eventManager;
		this.id = id;
		eventManager.register(id);
	}

	public void run() {
		int count = 0;
		while (true) {
			Event event = eventManager.getEvent(id);
			if(event == null) continue;
			if (event.getId() == -1) {
				for (String tmp : sorted_lines)
					eventManager.postEvent(new Event(++count, id, tmp));
				eventManager.postEvent(event);
				break;
			}
			sorted_lines.add(event.getMessage());
		}
	}
}
