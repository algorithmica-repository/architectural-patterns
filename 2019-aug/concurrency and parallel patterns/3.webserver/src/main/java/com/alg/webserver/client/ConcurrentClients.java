package com.alg.webserver.client;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import com.alg.common.app.domain.Constants;
import com.alg.common.app.domain.WDI;
import com.alg.common.app.domain.WDILoader;

public class ConcurrentClients {

	public static void main(String[] args) {
		final int numClients = 2;
		List<WDI> data = WDILoader.load();
		System.out.println("Number of Simultaneous Clients: " + numClients);
		Thread[] threads = new Thread[numClients];
		for (int j = 0; j < numClients; j++) {
			SerialClient client = new SerialClient(data);
			threads[j] = new Thread(client);
			threads[j].start();
		}

		for (int j = 0; j < numClients; j++) {
			try {
				threads[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*
		 * try { Socket socket = new Socket("localhost", Constants.SERIAL_PORT);
		 * PrintWriter out = new PrintWriter(socket.getOutputStream(), true); Scanner in
		 * = new Scanner(socket.getInputStream());
		 * 
		 * StringWriter writer = new StringWriter(); writer.write("z");
		 * writer.write(";");
		 * 
		 * String command = writer.toString(); out.println(command); String output =
		 * in.nextLine();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

}
