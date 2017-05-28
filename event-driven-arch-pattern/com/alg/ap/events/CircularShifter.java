

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
			System.out.println("shifter received event" + event);
			if (event == null) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				continue;
			}
			if (event.getId() == -1) {
				event.setSourceid(id);
				eventManager.postEvent(event);
				break;
			}
			String line = event.getMessage();
			int nshifts = line.split(" ").length;			
			for (int i = 0; i < nshifts; ++i) {
				Event shift_event = new Event();
				int pos = line.indexOf(' ');
				String current = line.substring(0, pos);
				String tmp = line.substring(pos + 1) + " " + current;
				shift_event.setId(i);
				shift_event.setMessage(tmp);
				shift_event.setSourceid(id);
				eventManager.postEvent(shift_event);
				System.out.println("Shifter is posting event:" + shift_event);
				line = tmp;
			}
		}

	}

}
