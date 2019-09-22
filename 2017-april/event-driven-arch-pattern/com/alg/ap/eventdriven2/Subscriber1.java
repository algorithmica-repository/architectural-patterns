package com.alg.ap.eventdriven2;

public class Subscriber1 {	
	private EventBus eventBus;

	public Subscriber1(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
		eventBus.register(this);
	}
	
	public void push() throws Exception {
		eventBus.post("");		
	}
	
	@Subscribe
	public void receive(String event) {
		
	}

}
