package com.mycompany.frs_maven.model.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mycompany.frs_maven.configuration.DIConfiguration;
import com.mycompany.frs_maven.model.domain.Traveler;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TravelerMgrTest extends TestCase {
	static private Logger logger = LogManager.getLogger();
	
	public void testACreateProfile() {
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		Traveler traveler = new Traveler();
		traveler.setUsername("cr4zytrain666");
		traveler.setPassword("g0g0g0");
		traveler.setName("Ozzy Osbourne");
		traveler.setAddress("55 Evil Ave. Los Angeles, CA 96669");
		traveler.setCreditCardNumber("1122666899094774");
		traveler.setExpirationDate("12/28");
		try {
			TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
			success = travelerManager.createProfile(traveler);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("TravelerSvcTest.testACreateProfile failed!", success);
	}
	
	public void testBFetchProfile() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		Traveler traveler = null;
		try {
			TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
			traveler = travelerManager.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("TravelerSvcTest.testBFetchProfile failed!", traveler.getName().equals("Ozzy Osbourne"));
	}
	
	public void testCUpdateProfile() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		boolean success = false;
		Traveler traveler = new Traveler();
		Traveler updatedTraveler = new Traveler();
		traveler.setUsername("cr4zytrain666");
		traveler.setCreditCardNumber("9999666699996666");
		try {
			TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
			success = travelerManager.updateProfile(traveler);
			updatedTraveler = travelerManager.fetchProfile("cr4zytrain666");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertEquals("TravelerSvcTest.testCUpdateProfile failed!", updatedTraveler.getCreditCardNumber(), "9999666699996666");
		Assert.assertTrue("TravelerSvcTest.testCUpdateProfile failed!", success);
	}
	
	public void testDDeleteProfile() {
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		try {
			TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
			success = travelerManager.deleteProfile("cr4zytrain666");
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("TravelerSvcTest.testDDeleteProfile failed!", success);
	}
	
	public void testEPrintAllTravelers() {
		Boolean success = false;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		try {
			TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
			travelerManager.printAllTravelers();
			success = true;
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		context.close();
		Assert.assertTrue("TravelerSvcTest.testEPrintAllTravelers failed!", success);
	}
}