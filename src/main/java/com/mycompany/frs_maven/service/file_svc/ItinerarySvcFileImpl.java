package com.mycompany.frs_maven.service.file_svc;
import java.util.ArrayList;

import com.mycompany.frs_maven.domain.Itinerary;
import com.mycompany.frs_maven.domain.Traveler;
import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.service.Factory;
import com.mycompany.frs_maven.service.IItinerarySvc;
import com.mycompany.frs_maven.service.ITravelerSvc;

public class ItinerarySvcFileImpl implements IItinerarySvc {
	private ITravelerSvc travelerSvc;
	private void setup() throws ServiceLoadException {
		Factory factory = Factory.getInstance();
		travelerSvc = (ITravelerSvc) factory.getService(ITravelerSvc.NAME);
	}
	
	public Itinerary fetchItinerary(String itineraryId, String username) throws RecordNotFoundException, ServiceLoadException {
		setup();
		Traveler profile = travelerSvc.fetchProfile(username);
		ArrayList<Itinerary> il = profile.getItineraryList();
		Itinerary itinerary = new Itinerary();
		for(int i = 0; i < il.size(); i++) {
			Itinerary ci = il.get(i);
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
		ArrayList<Itinerary> currentList = profile.getItineraryList();
		
		// Update itinerary status to 'Reserved'
		itinerary.setStatus("Reserved");
		
		if(currentList == null ) {
			// If no itinerary list on profile, create one
			ArrayList<Itinerary> newList = new ArrayList<Itinerary>();
			newList.add(itinerary);
			travelerSvc.updateItineraryList(username, newList);
			didWrite = true;
		} else {
			currentList.add(itinerary);
			// Update profile data in database
			travelerSvc.updateItineraryList(username, currentList);
			didWrite = true;
		}
		
		return didWrite;
	}
	
	// TODO
	public boolean bookItinerary(String itineraryId, String username) {
		boolean didWrite = true;
		// Fetch profile data from database with given username
		// Locate itinerary matching the provided Id in traveler's itinerary list
		// Update itinerary status to 'Booked'
		// Update profile data in database
		// Return boolean based on success
		return didWrite;
	}
	
	public boolean deleteItinerary(String itineraryId, String username) throws ServiceLoadException, RecordNotFoundException {
		setup();
		boolean didDelete = true;
		// delete data
		return didDelete;
	}
	
}
