package com.mycompany.frs_maven.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightReservationSystemServerManager extends BaseServerManager {
	static private Logger logger = LogManager.getLogger();
	private static FlightReservationSystemServerManager _instance;
	private FlightReservationSystemServerManager() {}
	public static synchronized FlightReservationSystemServerManager getInstance() {
		if(_instance == null) {
			_instance = new FlightReservationSystemServerManager();
		}
		return _instance;
	}
	
	public boolean performAction(String commandString) {
		Boolean status = false;
		logger.error("commandString: " + commandString);
		return status;
	}
	
}