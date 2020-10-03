package com.mycompany.frs_maven.controller;
import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import com.mycompany.frs_maven.model.domain.Flight;

import junit.framework.TestCase;

public class FlightControllerTest extends TestCase {
	static private Logger logger = LogManager.getLogger();

//	public void testFetchFlight() {
//		FlightReservationSystemController controller = new FlightReservationSystemController();
//		DTO dtoOut = new DTO();
//		dtoOut.setCommandString("fetchFlight");
//		Flight flight = new Flight();
//		flight.setFlightNumber("AA01");
//		dtoOut.setData(flight);
//		DTO dtoIn = controller.performAction(dtoOut);
//		Flight fetchedFlight = (Flight)dtoIn.getData();
//		logger.error("status: " + dtoIn.getStatus());
//		logger.error("airlineCode: " + fetchedFlight.getAirlineCode());
//		Assert.assertTrue("FlightReservationSystemControllerTest.testFetchFlight failed!", fetchedFlight.getAirlineCode().equals("AA"));
//	}
	
	public void testFetchAllFlights() {
		FlightController controller = new FlightController();
		ArrayList<Flight> fetchedFlights = controller.fetchAllFlights();
		logger.error(fetchedFlights.size());
	}
}