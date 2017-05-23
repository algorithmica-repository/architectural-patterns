package com.alg.ap.events;

import java.io.BufferedReader;
import java.io.FileReader;

public class InputReader extends Thread {
	private EventManager eventManager;
	private String infile;
	private String id;

	public InputReader(EventManager eventManager, String infile, String id) {
		this.eventManager = eventManager;
		eventManager.register(id);
		this.id = id;
		this.infile = infile;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(infile));
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				Event event = new Event(++count, id, line);
				eventManager.postEvent(event);
			}
			eventManager.postEvent(new Event(-1,null, null));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
