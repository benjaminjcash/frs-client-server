package com.mycompany.frs_maven.model.business;

import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.frs_maven.model.domain.Traveler;
import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.service.Factory;
import com.mycompany.frs_maven.model.service.ITravelerSvc;

public class TravelerMgr {
	private ITravelerSvc travelerSvc;
	private void setup() throws ServiceLoadException {
		Factory factory = Factory.getInstance();
		travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
	}
	
	public boolean createProfile(Traveler traveler) throws ServiceLoadException, ClassNotFoundException, IOException {
		setup();
		return travelerSvc.createProfile(traveler);
	}
	
	public Traveler fetchProfile(String username) throws ServiceLoadException {
		setup();
		return travelerSvc.fetchProfile(username);
	}
	
	public boolean deleteProfile(String username) throws ServiceLoadException {
		setup();
		return travelerSvc.deleteProfile(username);
	}
	
	public ArrayList<Traveler> fetchAllProfiles() throws ServiceLoadException {
		setup();
		return travelerSvc.fetchAllProfiles();
	}
}
