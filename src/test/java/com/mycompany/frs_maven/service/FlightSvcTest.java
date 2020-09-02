package com.mycompany.frs_maven.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import junit.framework.TestCase;

public class FlightSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private IFlightSvc flightSvc;
	
	public void testPrintAllFlights() {
		Factory factory = Factory.getInstance();
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			flightSvc.printAllFlights();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
}