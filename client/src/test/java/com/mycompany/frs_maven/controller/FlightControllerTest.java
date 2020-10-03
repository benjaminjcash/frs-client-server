package com.mycompany.frs_maven.controller;
import java.time.LocalDateTime;

import java.time.Month;
import java.util.ArrayList;
import org.junit.Assert;

import com.mycompany.frs_maven.model.domain.Flight;

import junit.framework.TestCase;

public class FlightControllerTest extends TestCase {

	public void testFetchFlight() {
		FlightController controller = new FlightController();
		Flight fetchedFlight = controller.fetchFlight("AA01");
		Assert.assertTrue("FlightReservationSystemControllerTest.testFetchFlight failed!", fetchedFlight.getAirlineCode().equals("AA"));
	}
	
	public void testFetchAllFlights() {
		FlightController controller = new FlightController();
		ArrayList<Flight> fetchedFlights = controller.fetchAllFlights();
		Assert.assertTrue("FlightControllerTest.testFetchAllFlights failed!", fetchedFlights.size() > 0);
	}
	
	public void testCreateFlight() {
		FlightController controller = new FlightController();
		Flight flight = new Flight();
		flight.setFlightNumber("AM32");
		flight.setAirlineCode("AM");
		flight.setDepartureCode("AZT");
		flight.setArrivalCode("AZO");
		flight.setDepartureTime(LocalDateTime.of(2020, Month.JULY, 20, 16, 30, 00));
		flight.setArrivalTime(LocalDateTime.of(2020, Month.JULY, 20, 23, 30, 00));
		flight.setEconomyTicket(650);
		flight.setBusinessTicket(780);
		Boolean status = controller.createFlight(flight);
		Assert.assertTrue("FlightControllerTest.testCreateFlight failed!", status);
	}
}