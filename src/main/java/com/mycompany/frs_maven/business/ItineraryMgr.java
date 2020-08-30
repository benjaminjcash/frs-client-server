package com.mycompany.frs_maven.business;

import com.mycompany.frs_maven.domain.Itinerary;
import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.service.Factory;
import com.mycompany.frs_maven.service.IItinerarySvc;

public class ItineraryMgr {
	private IItinerarySvc itinerarySvc;
	private void setup() throws ServiceLoadException {
		Factory factory = Factory.getInstance();
		itinerarySvc = (IItinerarySvc) factory.getService(IItinerarySvc.NAME);
	}
	
	public Itinerary fetchItinerary(String itineraryId, String username) throws ServiceLoadException, RecordNotFoundException {
		setup();
		return itinerarySvc.fetchItinerary(itineraryId, username);
	}
	
	public boolean reserveItinerary(Itinerary itinerary, String username) throws ServiceLoadException {
		setup();
		return itinerarySvc.reserveItinerary(itinerary, username);
	}
	
	public boolean bookItinerary(String itineraryId, String username) throws ServiceLoadException {
		setup();
		return itinerarySvc.bookItinerary(itineraryId, username);
	}

	public boolean deleteItinerary(String itineraryId, String username) throws ServiceLoadException, RecordNotFoundException {
		setup();
		return itinerarySvc.deleteItinerary(itineraryId, username);
	}
}
