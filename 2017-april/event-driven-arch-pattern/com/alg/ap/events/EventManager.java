package com.alg.ap.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EventManager implements IEventManager {
	private Map<String, EventQueue> event_queues;
	
	public EventManager() {
		event_queues = new HashMap<String, EventQueue>();
	}

	@Override
	public synchronized void register(String name) {
		event_queues.put(name, new EventQueue(name));
	}

	@Override
	public synchronized void unregister(String id) {
		for(String eq:event_queues.keySet()) {
			if(eq.equals(id))
				event_queues.remove(eq);
		}		
	}

	@Override
	public synchronized void postEvent(Event event) {
		for(Entry<String, EventQueue> entry:event_queues.entrySet()) {
			if(entry.getKey().equals(event.getSourceid()))
				entry.getValue().addEvent(event);
		}
		
	}

	@Override
	public Event getEvent(String id) {
		if(id.equals("cs"))
			id = "ir";
		else if(id.equals("sorter"))
			id = "cs";
		else if(id.equals(("ow")))
			id = "sorter";
		return event_queues.get(id).fetchEvent();
	}
	
}
