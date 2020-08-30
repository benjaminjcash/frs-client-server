package com.mycompany.frs_maven.service;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.frs_maven.domain.Itinerary;
import com.mycompany.frs_maven.domain.Traveler;
import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;

public class ItinerarySvcImpl implements IItinerarySvc {
	private ITravelerSvc travelerSvc;
	private void setup() throws ServiceLoadException {
		Factory factory = Factory.getInstance();
		travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
	}
	
	public Itinerary fetchItinerary(String itineraryId, String username) throws RecordNotFoundException, ServiceLoadException {
		setup();
		Traveler profile = travelerSvc.fetchProfile(username);
		Itinerary[] il = profile.getItineraryList();
		Itinerary itinerary = new Itinerary();
		for(int i = 0; i < il.length; i++) {
			Itinerary ci = il[i];
			String id = ci.getId();
			if(id.equals(itineraryId)) {
				itinerary = ci;
			}
		}
		String ciid = itinerary.getId();
		if(ciid != null && ciid.equals(itineraryId)) {
			return itinerary;
		} else {
			throw new RecordNotFoundException("itinerary not found");
		}
	}
	
	public boolean reserveItinerary(Itinerary itinerary, String username) throws ServiceLoadException {
		boolean didWrite = false;
		
		// Fetch profile data from database with given username
		Factory factory = Factory.getInstance();
		ITravelerSvc travelerSvc = (ITravelerSvc)factory.getService(ITravelerSvc.NAME);
		Traveler profile = travelerSvc.fetchProfile(username);
		// Get current list
		Itinerary[] currentList = profile.getItineraryList();
		
		// Update itinerary status to 'Reserved'
		itinerary.setStatus("Reserved");
		
		if(currentList == null ) {
			// If no itinerary list on profile, create one
			Itinerary[] newList = new Itinerary[] { itinerary };
			travelerSvc.updateItineraryList(username, newList);
			didWrite = true;
		} else {
			// Add new itinerary to list
			int size = currentList.length + 1;
			Itinerary[] newList = new Itinerary[size];
			for(int i = 0; i < newList.length; i++) {
				if(i == 0) {
					newList[0] = itinerary;
				} else {
					newList[i] = currentList[i-1];
				}
			}
			// Update profile data in database
			travelerSvc.updateItineraryList(username, newList);
			didWrite = true;
		}
		
		return didWrite;
	}
	
	// TODO
	public boolean bookItinerary(String itineraryId, String username) {
		// Fetch profile data from database with given username
		// Locate itinerary matching the provided Id in traveler's itinerary list
		// Update itinerary status to 'Booked'
		// Update profile data in database
		// Return boolean based on success
		boolean didWrite = true;
		return didWrite;
	}
	
	public boolean deleteItinerary(String itineraryId, String username) throws ServiceLoadException, RecordNotFoundException {
		setup();
		boolean didDelete = false;
		Traveler profile = travelerSvc.fetchProfile(username);
		Itinerary[] cis = profile.getItineraryList();
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < cis.length; i++) {
			Itinerary ci = cis[i];
			String id = ci.getId();
			if(id.equals(itineraryId)) {
				indices.add(i);
			}
		}
		int size = cis.length - indices.size();
		if(size > 0) {
			Itinerary[] newItineraries = new Itinerary[size];
			int j = 0;
			for(int i = 0; i < cis.length; i++) {
				if(!indices.contains(i)) {
					newItineraries[j] = cis[i];
					j++;
				}
			}
			travelerSvc.updateItineraryList(username, newItineraries);
			didDelete = true;
		} else {
			throw new RecordNotFoundException("No record found");
		}
		
		return didDelete;
	}
	
}
