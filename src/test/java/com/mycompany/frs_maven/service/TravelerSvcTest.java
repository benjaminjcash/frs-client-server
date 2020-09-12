package com.mycompany.frs_maven.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import com.mycompany.frs_maven.domain.Traveler;

import junit.framework.TestCase;

public class TravelerSvcTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	private ITravelerSvc travelerSvc;
	
	public void testCreateProfile() {
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
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.createProfile(traveler);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testCreateProfile failed!", success);
	}
	
	public void testFetchProfile() {
		Factory factory = Factory.getInstance();
		Traveler traveler = null;
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			traveler = travelerSvc.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertNotNull(traveler);
	}
	
	public void testUpdateProfile() {
		Factory factory = Factory.getInstance();
		boolean success = false;
		Traveler traveler = new Traveler();
		Traveler updatedTraveler = new Traveler();
		traveler.setUsername("cr4zytrain666");
		traveler.setCreditCardNumber("9999666699996666");
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.updateProfile(traveler);
			updatedTraveler = travelerSvc.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertEquals("TravelerSvcTest.testUpdateProfile failed!", updatedTraveler.getCreditCardNumber(), "9999666699996666");
		Assert.assertTrue("TravelerSvcTest.testUpdateProfile failed!", success);
	}
	
	public void testDeleteProfile() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			success = travelerSvc.deleteProfile("cr4zytrain666");
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testDeleteProfile failed!", success);
	}
	
	public void testPrintAllTravelers() {
		Boolean success = false;
		Factory factory = Factory.getInstance();
		try {
			travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
			travelerSvc.printAllTravelers();
			success = true;
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue("TravelerSvcTest.testPrintAllTravelers failed!", success);
	}
}