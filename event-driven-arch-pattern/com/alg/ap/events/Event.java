package com.alg.ap.events;

public class Event {
	private int id;
	private String sourceid;
	private String message;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int id, String sourceid, String message) {
		super();
		this.id = id;
		this.sourceid = sourceid;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getSourceid() {
		return sourceid;
	}

	public String getMessage() {
		return message;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
