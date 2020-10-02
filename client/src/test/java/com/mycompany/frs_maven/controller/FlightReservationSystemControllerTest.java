package com.mycompany.frs_maven.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import junit.framework.TestCase;

public class FlightReservationSystemControllerTest extends TestCase {
static private Logger logger = LogManager.getLogger();
	
	public void testPerformAction() {
		FlightReservationSystemController controller = new FlightReservationSystemController();
		Boolean status = controller.performAction("test connection");
		logger.error("status: " + status);
	}
}