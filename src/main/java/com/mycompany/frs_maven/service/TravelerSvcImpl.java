package com.mycompany.frs_maven.service;
import com.mycompany.frs_maven.domain.Traveler;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.domain.Flight;
import com.mycompany.frs_maven.domain.Itinerary;

public class TravelerSvcImpl implements ITravelerSvc {
	static private Logger logger = LogManager.getLogger();
	
	public Traveler fetchProfile(String username) {
		Traveler traveler = new Traveler();
		try {
			traveler = getRecord(username);
		}
		catch(RecordNotFoundException e) {
//			System.out.println(e.getMessage());
			return null;
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			return null;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			return null;
		}
		return traveler;
	}
	
	public boolean createProfile(Traveler traveler) throws IOException, ClassNotFoundException {
		boolean didWrite = true;
		try {
			didWrite = addRecord(traveler);
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		return didWrite;
	}
	
	public boolean deleteProfile(String username) {
		boolean didDelete;
		try {
			didDelete = deleteRecord(username);
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			didDelete = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didDelete = false;
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
			didDelete = false;
		}
		return didDelete;
	}
	
	public Traveler[] fetchAllProfiles() {
		Traveler[] travelers = new Traveler[]{};
		try {
			travelers = getRecords();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		return travelers;
	}
	
	public boolean addPaymentInformation(String username, String creditCardNumber, String expirationDate) {
		boolean didWrite = true;
		Traveler toBeUpdated = new Traveler();
		try {
			toBeUpdated = getRecord(username);
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		
		deleteProfile(username);
		toBeUpdated.setCreditCardNumber(creditCardNumber);
		toBeUpdated.setExpirationDate(expirationDate);

		try {
			addRecord(toBeUpdated);
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		
		return didWrite;
	}
	
	public boolean updateItineraryList(String username, Itinerary[] list) {
		boolean didWrite = true;
		Traveler toBeUpdated = fetchProfile(username);
		deleteProfile(username);
		toBeUpdated.setItineraryList(list);
		try {
			createProfile(toBeUpdated);
		}
		catch(IOException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		return didWrite;
	}
	
	public Traveler[] getRecords() throws IOException, ClassNotFoundException, RecordNotFoundException {
		String filePath = "src/main/java/com/mycompany/frs_maven/data/travelers.data";
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath));
		Traveler[] travelers = (Traveler[]) input.readObject();
		input.close();
 		return travelers;
	}
	
	public boolean addRecords(Traveler[] data) throws IOException, ClassNotFoundException {
		boolean success = true;
		String filePath = "src/main/java/com/mycompany/frs_maven/data/travelers.data";
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath));
		output.writeObject(data);
		output.flush();
		output.close();
		return success;	
	}
	
	private Traveler getRecord(String username) throws IOException, ClassNotFoundException, RecordNotFoundException {
		Traveler[] allRecords = getRecords();
		Traveler requestedTraveler = new Traveler();
		if(allRecords.length > 0) {
			for(Traveler t: allRecords) {
				String fetchedUsername = t.getUsername();
				if(username.equals(fetchedUsername)) {
					requestedTraveler = t;
				}
			}
		}
		if(requestedTraveler.getUsername() == null) {
			throw new RecordNotFoundException("No record found");
		}
		return requestedTraveler;
	}
	
	private boolean addRecord(Traveler data) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		Traveler[] currentRecords = getRecords();
		int size = currentRecords.length + 1;
		Traveler[] newRecords = new Traveler[size];
		for(int i = 0; i < newRecords.length; i++) {
			if(i == 0) {
				newRecords[0] = data;
			} else {
				newRecords[i] = currentRecords[i-1];
			}
		}
		success = addRecords(newRecords);
		return success;
	}
	
	private boolean deleteRecord(String username) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		
		Traveler[] currentRecords = getRecords();
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < currentRecords.length; i++) {
			String currUsername = currentRecords[i].getUsername();
			if(currUsername.equals(username)) {
				indices.add(i);
			}
		}
		int size = currentRecords.length - indices.size();
		if(size > 0) {
			Traveler[] newRecords = new Traveler[size];
			int j = 0;
			for(int i = 0; i < currentRecords.length; i++) {
				if(!indices.contains(i)) {
					newRecords[j] = currentRecords[i];
					j++;
				}
			}
			addRecords(newRecords);
		} else {
			throw new RecordNotFoundException("No record found");
		}
		
		return success;
	}
	
	public void printAllTravelers() throws IOException, ClassNotFoundException, RecordNotFoundException {
		Traveler[] allTravelers = getRecords();
		System.out.println("=== All Travelers ===");
		System.out.println("");		
		for(int i = 0; i < allTravelers.length; i++) {
			Traveler curr = allTravelers[i];
			System.out.println((i + 1) + ". " + curr.getName());
			System.out.println("   Address: " + curr.getAddress());
			System.out.println("   Username: " + curr.getUsername());
			System.out.println("   Password: " + curr.getPassword());
			System.out.println("   Credit Card Number: " + curr.getCreditCardNumber());
			System.out.println("   Credit Card Expiration Date: " + curr.getExpirationDate());
			System.out.println("");
		}
	}
	
	public void printItineraries(String username) throws IOException, ClassNotFoundException, RecordNotFoundException {
		Traveler traveler = fetchProfile(username);
		
		if(traveler == null) {
			throw new RecordNotFoundException("profile not found");
		}
		
		System.out.println("=== " + traveler.getName() + "'s Itineraries ===");
		Itinerary[] il = traveler.getItineraryList();
		
		if(il == null) {
			throw new RecordNotFoundException("no itineraries found");
		}
		
		for(int i = 0; i < il.length; i++) {
			Itinerary cr = il[i];
			System.out.println((i + 1) + ". Itinerary " + cr.getId());
			Flight[] fls = cr.getFlights();
			
			if(fls == null) {
				throw new RecordNotFoundException("no flights found");
			}
				
			for(int j = 0; j < fls.length; j++) {
				System.out.println("  Flight " + (j + 1) + ": " + fls[j].getFlightNumber());
			}
		}
	}
}
