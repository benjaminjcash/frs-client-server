package com.mycompany.frs_maven.server;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightReservationSystemServer {
	static private Logger logger = LogManager.getLogger();
	
	private FlightReservationSystemServer() {}
	
	public static void startServer() {
		logger.error("frs server listening...");
		try {
			@SuppressWarnings("resource")
			ServerSocket s = new ServerSocket(8000);
			for(;;) {
				Socket socket = s.accept();
				FlightReservationSystemServerHandler serverHandler = new FlightReservationSystemServerHandler(socket);
				serverHandler.run();
				
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		FlightReservationSystemServer.startServer();
	}
}