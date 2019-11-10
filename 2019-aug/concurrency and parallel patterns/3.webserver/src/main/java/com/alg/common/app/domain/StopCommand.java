package com.alg.common.app.domain;

public class StopCommand extends Command {

	public StopCommand(String[] command, WDIService wdiService) {
		super(command, wdiService);
	}

	public String execute() {
		return "Server stopped";
	}

}
