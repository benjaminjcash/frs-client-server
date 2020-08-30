package com.mycompany.frs_maven.service;
import com.mycompany.frs_maven.domain.Itinerary;
import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;

public interface IItinerarySvc extends IService {
	
	public final String NAME = "IItinerarySvc";
	
	public Itinerary fetchItinerary(String itineraryId, String username) throws RecordNotFoundException, ServiceLoadException;
	
	public boolean reserveItinerary(Itinerary itinerary, String username) throws ServiceLoadException;
	
	public boolean bookItinerary(String itineraryId, String username);
	
	public boolean deleteItinerary(String itineraryId, String username) throws ServiceLoadException, RecordNotFoundException;
}
