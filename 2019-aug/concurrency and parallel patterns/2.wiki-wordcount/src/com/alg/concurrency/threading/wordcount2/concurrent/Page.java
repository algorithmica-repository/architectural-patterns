package com.alg.concurrency.threading.wordcount2.concurrent;

class Page {
	private String title;
	private String text;

	public Page(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
}
