package com.alg.webserver.serial;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.alg.common.app.domain.Command;
import com.alg.common.app.domain.ErrorCommand;
import com.alg.common.app.domain.QueryCommand;
import com.alg.common.app.domain.ReportCommand;
import com.alg.common.app.domain.WDIService;

public class EventHandler {

	public static void handle(Socket socket, WDIService wdiService) {

		try {
			Scanner in = new Scanner(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Command command;

			String line = in.nextLine();
			String[] commandData = line.split(";");
			System.out.println("Command: " + commandData[0]);
			switch (commandData[0]) {
			case "q":
				System.out.println("Query");
				command = new QueryCommand(commandData, wdiService);
				break;
			case "r":
				System.out.println("Report");
				command = new ReportCommand(commandData, wdiService);
				break;
			default:
				System.out.println("Error");
				command = new ErrorCommand(commandData, wdiService);
			}
			String response = command.execute();
			System.out.println(response);
			out.println(response);
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {

		}
	}

}
