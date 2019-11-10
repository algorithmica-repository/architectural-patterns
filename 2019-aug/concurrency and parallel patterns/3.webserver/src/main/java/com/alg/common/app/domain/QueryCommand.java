package com.alg.common.app.domain;

public class QueryCommand extends Command {

	public QueryCommand(String[] command, WDIService wdiService) {
		super(command, wdiService);
	}

	public String execute() {

		if (command.length == 3) {
			return wdiService.query(command[1], command[2]);
		} else {
			return "ERROR;Bad Command";
		}
	}

}
