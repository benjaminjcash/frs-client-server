package com.mycompany.frs_maven.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.mycompany.frs_maven.domain.Flight;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private IFlightSvc flightSvc;
	
	public void testAPublishFlight() {
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
		Assert.assertTrue("FlightSvcTest.testAPublishFlight failed!", success);
	}
	
	public void testBFetchFlight() {
		Factory factory = Factory.getInstance();
		Flight flight = null;
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			flight = flightSvc.fetchFlight("AM32");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("FlightSvcTest.testBFetchFlight failed!", flight.getAirlineCode().equals("AM"));
	}

	public void testCUpdateFlight() {
		Factory factory = Factory.getInstance();
		boolean success = false;
		Flight flight = new Flight();
		Flight updatedFlight = new Flight();
		flight.setFlightNumber("AM32");
		flight.setArrivalCode("XXX");
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			success = flightSvc.updateFlight(flight);
			updatedFlight = flightSvc.fetchFlight("AM32");
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertEquals("FlightSvcTest.testCUpdateFlight failed!", updatedFlight.getArrivalCode(), "XXX");
		Assert.assertTrue("FlightSvcTest.testCUpdateFlight failed!", success);
	}

	public void testDDeleteFlight() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			success = flightSvc.deleteFlight("AM32");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("FlightSvcTest.testDDeleteFlight failed!", success);
	}

	public void testEPrintAllFlights() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			flightSvc.printAllFlights();
			success = true;
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("FlightSvcTest.testEPrintAllFlights failed!", success);
	}
}