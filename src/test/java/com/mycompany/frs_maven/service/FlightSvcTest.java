package com.mycompany.frs_maven.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.mycompany.frs_maven.domain.Flight;

import junit.framework.TestCase;

public class FlightSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private IFlightSvc flightSvc;
	
	public void testPublishFlight() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		Flight flight = new Flight();
		flight.setFlightNumber("AM32");
		flight.setAirlineCode("AM");
		flight.setDepartureCode("AZT");
		flight.setArrivalCode("AZO");
		flight.setDepartureTime(LocalDateTime.of(2020, Month.JULY, 20, 16, 30, 00));
		flight.setArrivalTime(LocalDateTime.of(2020, Month.JULY, 20, 23, 30, 00));
		flight.setEconomyTicket(650);
		flight.setBusinessTicket(780);
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			success = flightSvc.publishFlight(flight);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("FlightSvcTest.testPublishFlight failed!", success);
	}
	
	public void testFetchFlight() {
		Factory factory = Factory.getInstance();
		Flight flight = null;
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			flight = flightSvc.fetchFlight("AM32");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info(flight.getDepartureTime());
		Assert.assertNotNull(flight);
	}
	
//	public void testPrintAllFlights() {
//		Factory factory = Factory.getInstance();
//		try {
//			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
//			flightSvc.printAllFlights();
//		}
//		catch(Exception e) {
//			logger.error(e.getMessage());
//		}
//	}
}