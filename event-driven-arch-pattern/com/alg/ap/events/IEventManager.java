package com.alg.ap.events;

public interface IEventManager {
	void register(String id);
	void unregister(String id);
	void postEvent(Event event);
	Event getEvent(String id);
}
