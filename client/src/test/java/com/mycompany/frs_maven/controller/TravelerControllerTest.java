package com.mycompany.frs_maven.controller;

import org.junit.Assert;
import com.mycompany.frs_maven.model.domain.Traveler;

import junit.framework.TestCase;

public class TravelerControllerTest extends TestCase {
	
	public void testFetchProfile() {
		TravelerController controller = new TravelerController();
		Traveler fetchedProfile = controller.fetchProfile("frodorules99");
		Assert.assertTrue("FlightReservationSystemControllerTest.testFetchFlight failed!", fetchedProfile.getName().equals("Frodo Baggins"));
	}
}
