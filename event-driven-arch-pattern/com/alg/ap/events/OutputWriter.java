package com.alg.ap.events;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputWriter extends Thread {
	private EventManager eventManager;
	private String outfile;
	private String id;

	public OutputWriter(EventManager eventManager, String outfile, String id) {
		this.eventManager = eventManager;
		eventManager.register(id);
		this.id = id;
		this.outfile = outfile;
	}

	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
			while (true) {
				Event event = eventManager.getEvent(id);
				System.out.println("Output writer receiving event:" + event);
				if (event == null) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					continue;
				}
				if (event.getId() == -1) {
					bw.close();
					break;
				}
				bw.write(event.getMessage());
				bw.newLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
