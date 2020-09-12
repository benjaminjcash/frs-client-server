package com.mycompany.frs_maven.service;
import com.mycompany.frs_maven.domain.Traveler;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.exceptions.ServiceLoadException;

public interface ITravelerSvc extends IService {
	public final String NAME = "ITravelerSvc";
	
	public Traveler fetchProfile(String username);
	
	public boolean createProfile(Traveler traveler) throws IOException, ClassNotFoundException;
	
	public boolean deleteProfile(String username);
	
	public ArrayList<Traveler> fetchAllProfiles() throws ServiceLoadException;
	
	public void printAllTravelers() throws IOException, ClassNotFoundException, RecordNotFoundException;
}
