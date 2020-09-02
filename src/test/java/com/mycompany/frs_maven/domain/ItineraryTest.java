package com.mycompany.frs_maven.domain;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import junit.framework.TestCase;

public class ItineraryTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	
	public void testEquals() {
		logger.info("starting ItineraryTest.testEquals");
		
		Flight f1 = new Flight();
		f1.setFlightNumber("101");
		Flight f2 = new Flight();
		f2.setFlightNumber("999");
		
		ArrayList<Flight> fs1 = new ArrayList<Flight>();
		fs1.add(f1);
		fs1.add(f2);
		
		Itinerary i1 = new Itinerary();
		i1.setId("0012");
		i1.setFlights(fs1);
		
		Itinerary i2 = new Itinerary();
		i2.setId("0013");
		i2.setFlights(fs1);
		
		Itinerary i3 = new Itinerary();
		i3.setId("0012");
		i3.setFlights(fs1);
		
		assertFalse("i2 equals i1", i2.equals(i1));
		assertTrue("i3 equals i1", i3.equals(i1));
		
		logger.info("ItineraryTest.testEquals passed!");
	}
	
	public void testValidate() {
		logger.info("starting ItineraryTest.testValidate");
		
		Itinerary i1 = new Itinerary();
		i1.setId("0001");
		i1.setTotalCost(564.53);
		
		Flight f1 = new Flight();
		Flight f2 = new Flight();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		flights.add(f1);
		flights.add(f2);
		i1.setFlights(flights);
		
		i1.setStatus("Reserved");
		
		assertTrue("i1 is not valid Itinerary", i1.validate());
		
		logger.info("ItineraryTest.testValidate passed!");
	}
}
