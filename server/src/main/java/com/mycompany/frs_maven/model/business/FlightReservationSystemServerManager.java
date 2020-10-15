package com.mycompany.frs_maven.model.business;

import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.configuration.DIConfiguration;
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
	
	public synchronized DTO performAction(DTO dtoIn) {
		DTO dtoOut = new DTO();
		Boolean status = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		
		try {
			
			FlightMgr flightManager = context.getBean(FlightMgr.class);
			TravelerMgr travelerManager =context.getBean(TravelerMgr.class);
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
				case "fetchAllProfiles":
					ArrayList<Traveler> fetchedProfiles = travelerManager.fetchAllProfiles();
					dtoOut.setData(fetchedProfiles);
					status = true;
					break;
				case "deleteProfile":
					Traveler travelerToDelete = (Traveler)dtoIn.getData();
					status = travelerManager.deleteProfile(travelerToDelete.getUsername());
					break;
				case "createProfile":
					Traveler profileToBeWritten = (Traveler)dtoIn.getData();
					status = travelerManager.createProfile(profileToBeWritten);
					break;
				case "fetchProfile":
					Traveler profileToFetch = (Traveler)dtoIn.getData();
					Traveler fetchedProfile = travelerManager.fetchProfile(profileToFetch.getUsername());
					dtoOut.setData(fetchedProfile);
					status = true;
					break;
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		context.close();
		dtoOut.setStatus(status);
		return dtoOut;
	}
	
}