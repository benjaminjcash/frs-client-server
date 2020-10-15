package com.mycompany.frs_maven.model.business;

import java.io.IOException;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.frs_maven.model.domain.Traveler;
import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.service.ITravelerSvc;

@Component
public class TravelerMgr {
	private ITravelerSvc travelerSvc;
	
	@Autowired
	public void setTravelerService(ITravelerSvc travelerSvc) {
		this.travelerSvc = travelerSvc;
	}
	
	public synchronized boolean createProfile(Traveler traveler) throws ServiceLoadException, ClassNotFoundException, IOException {
		return this.travelerSvc.createProfile(traveler);
	}
	
	public synchronized Traveler fetchProfile(String username) throws ServiceLoadException {
		return this.travelerSvc.fetchProfile(username);
	}
	
	public synchronized boolean deleteProfile(String username) throws ServiceLoadException {
		return this.travelerSvc.deleteProfile(username);
	}
	
	public synchronized boolean updateProfile(Traveler traveler) throws RecordNotFoundException {
		return this.travelerSvc.updateProfile(traveler);
	}
	
	public synchronized ArrayList<Traveler> fetchAllProfiles() throws ServiceLoadException {
		return this.travelerSvc.fetchAllProfiles();
	}
	
	public synchronized void printAllTravelers() throws ClassNotFoundException, IOException, RecordNotFoundException {
		this.travelerSvc.printAllTravelers();
	}
}
