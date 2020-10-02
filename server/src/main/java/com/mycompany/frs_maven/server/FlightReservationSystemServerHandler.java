package com.mycompany.frs_maven.server;

import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.model.business.FlightReservationSystemServerManager;

public class FlightReservationSystemServerHandler {
	static private Logger logger = LogManager.getLogger();
	private Socket incomingSocket;
	
	public FlightReservationSystemServerHandler() {}
	public FlightReservationSystemServerHandler(Socket _incomingSocket) {
		incomingSocket = _incomingSocket;
	}
	
	public void run() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {
			in = new ObjectInputStream(incomingSocket.getInputStream());
			out = new ObjectOutputStream(incomingSocket.getOutputStream());
			String commandString = (String)in.readObject();
			FlightReservationSystemServerManager serverManager = FlightReservationSystemServerManager.getInstance();
			boolean status = serverManager.performAction(commandString);
			out.writeObject(status);
			out.flush();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally {
			try {
				if(in != null) in.close();
				if(out != null) out.close();
				if(incomingSocket != null) incomingSocket.close();
			}
			catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}