package com.mycompany.frs_maven.model.service;
import com.mycompany.frs_maven.model.domain.Traveler;

import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;

public interface ITravelerSvc extends IService {
	
	public final String NAME = "ITravelerSvc";
	
	public Traveler fetchProfile(String username);
	
	public boolean createProfile(Traveler traveler) throws IOException, ClassNotFoundException;
	
	public boolean deleteProfile(String username);
	
	public boolean updateProfile(Traveler traveler) throws RecordNotFoundException;
	
	public ArrayList<Traveler> fetchAllProfiles();
	
	public void printAllTravelers() throws IOException, ClassNotFoundException, RecordNotFoundException;
}
