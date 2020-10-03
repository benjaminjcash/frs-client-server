package com.mycompany.frs_maven.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mycompany.frs_maven.model.domain.*;

public class FlightController {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Flight> fetchAllFlights(){
		ArrayList<Flight> fetchedFlights = new ArrayList<Flight>();
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("fetchAllFlights");
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		Object data = dtoIn.getData();
		fetchedFlights = (ArrayList<Flight>)data;
		return fetchedFlights;
	}
	
	public Flight fetchFlight(String flightNumber) {
		Flight fetchedFlight = new Flight();
		DTO dtoOut = new DTO();
		Flight flight = new Flight();
		flight.setFlightNumber(flightNumber);
		dtoOut.setData(flight);
		dtoOut.setCommandString("fetchFlight");
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		fetchedFlight = (Flight)dtoIn.getData();
		return fetchedFlight;
	}
	
	public Boolean createFlight(Flight flight) {
		Boolean status = false;
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("createFlight");
		dtoOut.setData(flight);
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		status = (Boolean)dtoIn.getStatus();
		return status;
	}
	
	public Boolean deleteFlight(String flightNumber) {
		Boolean status = false;
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("deleteFlight");
		Flight flight = new Flight();
		flight.setFlightNumber(flightNumber);
		dtoOut.setData(flight);
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		status = (Boolean)dtoIn.getStatus();
		return status;
	}
}
