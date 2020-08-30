package com.mycompany.frs_maven.domain;
import java.time.LocalDateTime;
import java.time.Month;
import junit.framework.TestCase;
import org.apache.logging.log4j.*;

public class FlightTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	
	public void testEquals() {
		logger.info("starting FlightTest.testEquals");
		
		Flight f1 = new Flight();
		f1.setFlightNumber("101");
		Flight f2 = new Flight();
		f2.setFlightNumber("101");
		assertTrue("f2 does not equal f1", f2.equals(f1));
		
		Flight f3 = new Flight();
		f3.setFlightNumber("499");
		Flight f4 = new Flight();
		f4.setFlightNumber("750");
		assertFalse("f4 equals f3", f4.equals(f3));
		
		logger.info("FlightTest.testEquals passed!");
	}
	
	public void testValidate() {
		logger.info("starting FlightTest.testValidate");
		
		Flight f1 = new Flight();
		f1.setAirlineCode("WN");
		f1.setFlightNumber("770");
		f1.setDepartureCode("DEN");
		f1.setArrivalCode("ATL");
		
		LocalDateTime dep = LocalDateTime.of(2019, Month.DECEMBER, 8, 11, 20);
		LocalDateTime arr = LocalDateTime.of(2019, Month.DECEMBER, 12, 14, 45);
		
		f1.setDepartureTime(dep);
		f1.setArrivalTime(arr);
		assertTrue("f1 is not a valid Flight", f1.validate());
		
		logger.info("FlightTest.testValidate passed!");
	}
}
