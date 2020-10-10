package com.mycompany.frs_maven.server;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadedFRSServer {
	static private Logger logger = LogManager.getLogger();
	
	private ThreadedFRSServer() {}
	
	public static void startServer() {
		logger.error("frs server listening...");
		int i = 1;
		try {
			@SuppressWarnings("resource")
			ServerSocket s = new ServerSocket(8000);
			for(;;) {
				Socket socket = s.accept();
				logger.error("spawning thread..." + i);
				ThreadedFRSServerHandler serverHandler = new ThreadedFRSServerHandler(socket, i);
				serverHandler.start();
				i++;
				
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ThreadedFRSServer.startServer();
	}
}