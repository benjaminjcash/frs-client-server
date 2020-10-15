package com.mycompany.frs_maven.model.business;

import java.time.LocalDateTime;
import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.frs_maven.configuration.DIConfiguration;
import com.mycompany.frs_maven.model.domain.Flight;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightMgrTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	
	public void testAPublishFlight() {
		
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
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
			FlightMgr flightManager = context.getBean(FlightMgr.class);
			success = flightManager.publishFlight(flight);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("FlightSvcTest.testAPublishFlight failed!", success);
	}
	
	public void testBFetchFlight() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		Flight flight = null;
		try {
			FlightMgr flightManager = context.getBean(FlightMgr.class);
			flight = flightManager.fetchFlight("AM32");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("FlightSvcTest.testBFetchFlight failed!", flight.getAirlineCode().equals("AM"));
	}

	public void testDDeleteFlight() {
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		try {
			FlightMgr flightManager = context.getBean(FlightMgr.class);
			success = flightManager.deleteFlight("AM32");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("FlightSvcTest.testDDeleteFlight failed!", success);
	}

	public void testEPrintAllFlights() {
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		try {
			FlightMgr flightManager = context.getBean(FlightMgr.class);
			flightManager.printAllFlights();
			success = true;
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("FlightSvcTest.testEPrintAllFlights failed!", success);
	}
}