package com.alg.ap.eventdriven2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListenersRegistry {
	    // keep track of event and its registered subscribed methods
	    private Map<Class<?>, List<ListenerMethod>> registry =
	            new ConcurrentHashMap<Class<?>, List<ListenerMethod>>();
	    // cache to keep track of all listener object
	    private List<Object> subscriberCache = new CopyOnWriteArrayList<Object>();


	    void register(Object listener) {
	        if (subscriberCache.contains(listener)) {
                    System.out.println(listener + " has already been registered.");
	        }
	         subscriberCache.add(listener);

	        // extract all subscribed methods from the listener and its super class and interfaces.
	        List<ListenerMethod> subscribedMethods = ReflectionUtil.findSubscribedMethods(listener.getClass());
	        if (subscribedMethods == null || subscribedMethods.isEmpty()) {
	            System.out.println(listener + " does not have any method marked with @Subscribe.");
	        }

	        for (ListenerMethod listenerMethod : subscribedMethods) {
	            listenerMethod.target = listener;
	            Class<?> eventType = listenerMethod.eventType;
	            if (registry.containsKey(eventType)) {
	                List<ListenerMethod> listenerMethods = registry.get(eventType);

	                // check ListenerMethod's equals method
	                if (!listenerMethods.contains(listenerMethod)) {
	                    listenerMethods.add(listenerMethod);
	                    System.out.println(listenerMethod + " has been registered.");
	                } else {
	                    System.out.println(listenerMethod + " has already been registered.");
	                }
	            } else {
	                List<ListenerMethod> listenerMethods = new CopyOnWriteArrayList<ListenerMethod>();
	                listenerMethods.add(listenerMethod);
	                registry.put(listenerMethod.eventType, listenerMethods);
	                System.out.println(listenerMethod + " has been registered.");
	            }
	        }
	    }

	    /**
	     * De-registers a listener object.
	     * */
	    void deregister(Object listener) {
            for (Object object : subscriberCache) {
	                if (object.equals(listener)) {
	                    // found in strong cache, remove it break
	                    if (subscriberCache.remove(listener)) {
	                       System.out.println(listener + " removed from the subscriber cache.");
	                    }
	                    break;
	                }
	        }

	        // iterate the whole registry map
	        for (Map.Entry<Class<?>, List<ListenerMethod>> entry : registry.entrySet()) {
	            List<ListenerMethod> subscribedMethods = entry.getValue();
	            for (ListenerMethod listenerMethod : subscribedMethods) {
	                      if (listenerMethod.target.equals(listener)) {
	                        if (subscribedMethods.remove(listenerMethod)) {
	                            System.out.println(listenerMethod + " has been un-registered.");
	                        }
	                      }
	            }
	        }
	        
	    }

	    /**
	     * Get all registered subscriber information for an event.
	     * */
	    List<ListenerMethod> getSubscribers(Object event) {
	        if (event != null) {
	            Class<?> eventType = event.getClass();
	            // loop through the registry to get all subscribed method
	            if (registry.containsKey(eventType)) {
	                return registry.get(eventType);
	            }
	        }
	        return null;
	    }
	 	
}
