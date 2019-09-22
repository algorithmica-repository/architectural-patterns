package com.alg.ap.eventdriven2;

import java.util.List;

public class EventBus {
	 	private ListenersRegistry listenersRegistry;
	    private String tag = "";

	    public EventBus(String tag) {
	        listenersRegistry = new ListenersRegistry();
	        this.tag = tag;
	    }

	    /**
	     * Registers an event listener to the event bus
	     */
	    public void register(Object listener) {
	        if (listener == null) {
	            System.out.println("Null object can not be registered.");
	        } else {
	        System.out.println("Registering listener " + listener);
	        listenersRegistry.register(listener);
	        }
	    }

	    /**
	     * De-registers a listener object that has been registered with the event bus.
	     * After de-registration, the object cease to listen to any further event.
	     * */
	    public void deregister(Object listener) {
	        if (listener == null) {
	            System.out.println("Null object can not be de-registered.");
	        } else {
	        System.out.println("Un-Registering listener " + listener);
	        listenersRegistry.deregister(listener);
	        }
	    }

	    /**
	     * Posts an event to the event bus.
	     * */
	    public void post(Object event) throws Exception {
	        if (event == null) {
	           System.out.println("Null event can not be posted.");
	           return;
	        }
	        System.out.println("Event " + event + " has been posted to the bus " + tag);

	        List<ListenerMethod> subscribers = listenersRegistry.getSubscribers(event);
	        if (subscribers != null && !subscribers.isEmpty()) {
	                System.out.println("Total subscribers found for event " + event + " is = " + subscribers.size());
	                System.out.println("Dispatching event " + event);
	            for(ListenerMethod listenerMethod: subscribers) {
	            	listenerMethod.method.invoke(listenerMethod.target, event);
	            }
	        }
	    }


}
