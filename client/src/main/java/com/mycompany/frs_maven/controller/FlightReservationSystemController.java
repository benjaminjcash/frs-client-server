package com.mycompany.frs_maven.controller;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightReservationSystemController implements IInterceptingController {
	static private Logger logger = LogManager.getLogger();
	
	public boolean performAction(String commandString) {
		boolean status = false;
		Socket socket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			socket = new Socket(InetAddress.getLocalHost(), 8000);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			out.writeObject(commandString);
			in = new ObjectInputStream(socket.getInputStream());
			status = (Boolean)in.readObject();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally {
			try {
				if(in != null) in.close();
				if(out != null) out.close();
				if(socket != null) socket.close();
			}
			catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		return status;
	}
}