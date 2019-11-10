package com.alg.common.app.domain;

public abstract class Command {

	protected String[] command;
	protected WDIService wdiService;

	public Command(String[] command, WDIService wdiService) {
		this.command = command;
		this.wdiService = wdiService;
	}

	public abstract String execute();

}
