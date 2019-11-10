package com.alg.common.app.domain;

public class ReportCommand extends Command {

	public ReportCommand(String[] command, WDIService wdiService) {
		super(command, wdiService);
	}

	public String execute() {
		return wdiService.report(command[1]);
	}

}
