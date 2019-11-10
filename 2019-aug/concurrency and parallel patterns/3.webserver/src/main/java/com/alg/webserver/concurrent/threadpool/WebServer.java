package com.alg.webserver.concurrent.threadpool;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alg.common.app.domain.Constants;
import com.alg.common.app.domain.WDIService;

@SpringBootApplication(scanBasePackages = { "com.alg.common.app.domain", "com.alg.common.app.repository" })
@EnableJpaRepositories("com.alg.common.app.repository")
@EntityScan("com.alg.common.app.domain")
public class WebServer implements CommandLineRunner {
	@Autowired
	WDIService wdiService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebServer.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		wdiService.load();
		ServerSocket server = new ServerSocket(Constants.SERIAL_PORT);
		int threadPoolSize = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
		while (true) {
			Socket socket = server.accept();
			executor.execute(new EventHandler(socket, wdiService));
		}
		//executor.shutdown();
        //executor.awaitTermination(1, TimeUnit.DAYS);
	}

}
