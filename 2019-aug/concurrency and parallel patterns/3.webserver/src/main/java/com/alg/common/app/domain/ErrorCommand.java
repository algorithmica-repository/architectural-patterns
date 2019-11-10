package com.alg.common.app.domain;

public class ErrorCommand extends Command {

	public ErrorCommand(String[] command, WDIService wdiService) {
		super(command, wdiService);
	}

	public String execute() {
		return "Unknown command: " + command[0];
	}

}
