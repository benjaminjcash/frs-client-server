package com.mycompany.frs_maven.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import junit.framework.TestCase;

public class TravelerSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private ITravelerSvc travelerSvc;
	
	public void testPrintAllTravelers() {
		Factory factory = Factory.getInstance();
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			travelerSvc.printAllTravelers();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
}