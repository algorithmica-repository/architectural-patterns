package com.alg.ap.events;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
	private String id;
	private List<Event> events;
	
	public EventQueue(String id) {
		this.id = id;
		events = new ArrayList<Event>();
	}

	public String getId() {
		return id;
	}
	
	public void addEvent(Event e) {
		events.add(e);
	}
	
	public Event fetchEvent() {
		if(events.size() == 0) return null;
		return events.remove(0);
	}

}
