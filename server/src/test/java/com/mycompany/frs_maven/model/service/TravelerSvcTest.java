package com.mycompany.frs_maven.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.mycompany.frs_maven.model.business.FlightReservationSystemServerManager;
import com.mycompany.frs_maven.model.domain.Traveler;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TravelerSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private ITravelerSvc travelerSvc;
	
	public void testACreateProfile() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		Traveler traveler = new Traveler();
		traveler.setUsername("cr4zytrain666");
		traveler.setPassword("g0g0g0");
		traveler.setName("Ozzy Osbourne");
		traveler.setAddress("55 Evil Ave. Los Angeles, CA 96669");
		traveler.setCreditCardNumber("1122666899094774");
		traveler.setExpirationDate("12/28");
		try {
			FlightReservationSystemServerManager.getInstance(); // instantiate to load services.xml
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.createProfile(traveler);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testACreateProfile failed!", success);
	}
	
	public void testBFetchProfile() {
		Factory factory = Factory.getInstance();
		Traveler traveler = null;
		try {
			FlightReservationSystemServerManager.getInstance(); // instantiate to load services.xml
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			traveler = travelerSvc.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testBFetchProfile failed!", traveler.getName().equals("Ozzy Osbourne"));
	}
	
	public void testCUpdateProfile() {
		Factory factory = Factory.getInstance();
		boolean success = false;
		Traveler traveler = new Traveler();
		Traveler updatedTraveler = new Traveler();
		traveler.setUsername("cr4zytrain666");
		traveler.setCreditCardNumber("9999666699996666");
		try {
			FlightReservationSystemServerManager.getInstance(); // instantiate to load services.xml
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.updateProfile(traveler);
			updatedTraveler = travelerSvc.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertEquals("TravelerSvcTest.testCUpdateProfile failed!", updatedTraveler.getCreditCardNumber(), "9999666699996666");
		Assert.assertTrue("TravelerSvcTest.testCUpdateProfile failed!", success);
	}
	
	public void testDDeleteProfile() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.deleteProfile("cr4zytrain666");
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testDDeleteProfile failed!", success);
	}
	
	public void testEPrintAllTravelers() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			FlightReservationSystemServerManager.getInstance(); // instantiate to load services.xml
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			travelerSvc.printAllTravelers();
			success = true;
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testEPrintAllTravelers failed!", success);
	}
}