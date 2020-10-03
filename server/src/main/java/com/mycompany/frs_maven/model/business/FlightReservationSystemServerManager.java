package com.mycompany.frs_maven.model.business;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mycompany.frs_maven.model.domain.DTO;
import com.mycompany.frs_maven.model.domain.Flight;
import com.mycompany.frs_maven.model.domain.Traveler;

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
	
	public DTO performAction(DTO dtoIn) {
		DTO dtoOut = new DTO();
		Boolean status = false;
		
		try {
			FlightMgr flightManager = new FlightMgr();
			switch(dtoIn.getCommandString()) {
				case "fetchAllFlights":
					ArrayList<Flight> fetchedFlights = flightManager.fetchAllFlights();
					dtoOut.setData(fetchedFlights);
					status = true;
					break;
				case "fetchFlight":
					Flight flightToFetch = (Flight)dtoIn.getData();
					Flight fetchedFlight = flightManager.fetchFlight(flightToFetch.getFlightNumber());
					dtoOut.setData(fetchedFlight);
					status = true;
					break;
				case "createFlight":
					Flight flightToBeWritten = (Flight)dtoIn.getData();
					status = flightManager.publishFlight(flightToBeWritten);
					break;
				case "deleteFlight":
					Flight flightToDelete = (Flight)dtoIn.getData();
					status = flightManager.deleteFlight(flightToDelete.getFlightNumber());
					break;
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		dtoOut.setStatus(status);
		return dtoOut;
	}
	
}