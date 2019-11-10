package com.alg.webserver.client;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.alg.common.app.domain.Constants;
import com.alg.common.app.domain.WDI;

public class SerialClient implements Runnable {
	private Socket socket;
	private PrintWriter out;
	private Scanner in;
	private List<WDI> data;

	public SerialClient(List<WDI> data) {
		this.data = data;
	}

	private void startConnection() throws Exception {
		socket = new Socket("localhost", Constants.SERIAL_PORT);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new Scanner(socket.getInputStream());
	}

	private void closeConnection() throws Exception {
		in.close();
		out.close();
		socket.close();
	}

	public void run() {
		Random randomGenerator = new Random();

		try {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 9; j++) {
					startConnection();
					WDI wdi = data.get(randomGenerator.nextInt(data.size()));

					StringWriter writer = new StringWriter();
					writer.write("q");
					writer.write(";");
					writer.write(wdi.getCountryCode());
					writer.write(";");
					writer.write(wdi.getIndicatorCode());
					System.out.println("Query INPUT: " + writer);
					out.println(writer.toString());
					String output = in.nextLine();
					System.out.println("Query OUTPUT: " + output);
					closeConnection();

					startConnection();
					writer = new StringWriter();
					writer.write("r");
					writer.write(";");
					writer.write(wdi.getIndicatorCode());
					System.out.println("Report INPUT: " + writer);
					out.println(writer.toString());
					output = in.nextLine();
					System.out.println("Report OUTPUT: " + output);
					closeConnection();

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
