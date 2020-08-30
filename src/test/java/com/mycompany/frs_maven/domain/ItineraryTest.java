package com.mycompany.frs_maven.domain;
import junit.framework.TestCase;

public class ItineraryTest extends TestCase {
	public void testEquals() {
		System.out.println("starting ItineraryTest.testEquals");
		
		Flight f1 = new Flight();
		f1.setFlightNumber("101");
		Flight f2 = new Flight();
		f2.setFlightNumber("999");
		
		Flight[] fs1 = new Flight[] { f1, f2 };
		
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
		
		System.out.println("ItineraryTest.testEquals passed!");
	}
	
	public void testValidate() {
		System.out.println("starting ItineraryTest.testValidate");
		
		Itinerary i1 = new Itinerary();
		i1.setId("0001");
		i1.setTotalCost(564.53);
		
		Flight f1 = new Flight();
		Flight f2 = new Flight();
		Flight[] flights = new Flight[] { f1, f2 };
		i1.setFlights(flights);
		
		i1.setStatus("Reserved");
		
		assertTrue("i1 is not valid Itinerary", i1.validate());
		
		System.out.println("ItineraryTest.testValidate passed!");
	}
}
