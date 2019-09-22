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
			System.out.println("Sorter receiving event:" + event);
			if(event == null) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				continue;
			}
			if (event.getId() == -1) {
				for (String tmp : sorted_lines) {
					Event event2 = new Event(++count, id, tmp);
					eventManager.postEvent(event2);
					System.out.println("Sorter posting event:" + event2);
				}
				event.setSourceid(id);
				eventManager.postEvent(event);
				System.out.println("Sorter posting event:" + event);
				break;
			}
			sorted_lines.add(event.getMessage());
		}
	}
}
