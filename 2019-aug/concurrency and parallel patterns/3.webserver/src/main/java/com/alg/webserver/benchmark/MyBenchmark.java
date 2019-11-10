package com.alg.webserver.benchmark;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import com.alg.common.app.domain.Constants;
import com.alg.common.app.domain.WDI;
import com.alg.common.app.domain.WDILoader;
import com.alg.webserver.client.SerialClient;

@State(Scope.Benchmark)
public class MyBenchmark {

	@Param({ "2" })
	private int numClients;

	private List<WDI> data = WDILoader.load();

	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Fork(1)
	@Warmup(iterations = 10, time = 1, batchSize = 1)
	@Measurement(iterations = 10, time = 1, batchSize = 1)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void serialClients() {
		System.out.println("Serial: Number of Simultaneous Clients: " + numClients);
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
	}

	@TearDown(Level.Trial)
	public void shutdownSerial() {
		System.out.println("Shutting down serial server");
		try {
			Socket socket = new Socket("localhost", Constants.SERIAL_PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());

			StringWriter writer = new StringWriter();
			writer.write("z");
			writer.write(";");

			String command = writer.toString();
			out.println(command);
			String output = in.nextLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}

}
