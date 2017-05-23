package com.alg.ap.events;

public class CircularShifter extends Thread {
	private EventManager eventManager;
	private String id;

	public CircularShifter(EventManager eventManager, String id) {
		super();
		this.eventManager = eventManager;
		eventManager.register(id);
		this.id = id;
	}

	public void run() {
		while (true) {
			Event event = eventManager.getEvent(id);
			if(event == null) continue;
			if (event.getId() == -1) {
				eventManager.postEvent(event);
				break;
			}
			String line = event.getMessage();
			int nshifts = line.split(" ").length;
			Event shift_event = new Event();
			for (int i = 0; i < nshifts; ++i) {
				int pos = line.indexOf(' ');
				String current = line.substring(0, pos);
				String tmp = line.substring(pos + 1) + " " + current;
				shift_event.setId(i);
				shift_event.setMessage(tmp);
				shift_event.setSourceid(id);
				eventManager.postEvent(shift_event);
				line = tmp;
			}
		}

	}

}
