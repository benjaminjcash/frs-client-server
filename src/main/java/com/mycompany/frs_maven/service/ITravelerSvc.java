package com.mycompany.frs_maven.service;
import com.mycompany.frs_maven.domain.Traveler;


import java.io.IOException;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.domain.Itinerary;

public interface ITravelerSvc extends IService {
	public final String NAME = "ITravelerSvc";
	
	public Traveler fetchProfile(String username);
	
	public boolean createProfile(Traveler traveler) throws IOException, ClassNotFoundException;
	
	public boolean deleteProfile(String username);
	
	public Traveler[] fetchAllProfiles() throws ServiceLoadException;
	
	public boolean addPaymentInformation(String username, String creditCardNumber, String expirationDate);
	
	public boolean updateItineraryList(String username, Itinerary[] list);
	
	public boolean addRecords(Traveler[] travelers) throws IOException, ClassNotFoundException;
	
	public Traveler[] getRecords() throws IOException, ClassNotFoundException, RecordNotFoundException;
	
	public void printAllTravelers() throws IOException, ClassNotFoundException, RecordNotFoundException;
	
	public void printItineraries(String username) throws IOException, ClassNotFoundException, RecordNotFoundException;
}
