package com.mycompany.frs_maven.server;

import java.net.Socket;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.model.domain.DTO;
import com.mycompany.frs_maven.model.business.FlightReservationSystemServerManager;

public class ThreadedFRSServerHandler extends Thread {
	static private Logger logger = LogManager.getLogger();
	private Socket incomingSocket;
	private int counter;
	
	public ThreadedFRSServerHandler() {}
	public ThreadedFRSServerHandler(Socket _incomingSocket, int c) {
		incomingSocket = _incomingSocket;
		counter = c;
	}
	
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(incomingSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(incomingSocket.getOutputStream());
			DTO dtoIn = (DTO)in.readObject();
			logger.error("received request from client to perform action " + dtoIn.getCommandString() + "...");
			FlightReservationSystemServerManager serverManager = FlightReservationSystemServerManager.getInstance();
			DTO dtoOut = serverManager.performAction(dtoIn);
			out.writeObject(dtoOut);
			out.flush();
			if(in != null) in.close();
			if(out != null) out.close();
			if(incomingSocket != null) incomingSocket.close();
			Thread.sleep(10000);
			logger.error("thread " + counter + "... terminated.");
		}
		catch(Exception e) {
			logger.error(e);
		}
	}
}