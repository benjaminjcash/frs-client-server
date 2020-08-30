package com.mycompany.frs_maven.service;
import java.io.ObjectOutputStream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.domain.Flight;

public class FlightSvcImpl implements IFlightSvc {
	
	private Map<String, String> airports = new HashMap<>();
	private Map<String, String> airlines = new HashMap<>();
	
	public Flight fetchFlight(String flightNumber) {
		Flight flight = new Flight();
		try {
			flight = getRecord(flightNumber);
		}
		catch(RecordNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return flight;
	}

	
	public boolean publishFlight(Flight flight) {
		boolean didWrite;
		try {
			didWrite = addRecord(flight);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());	
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			didWrite = false;
		}
		catch(RecordNotFoundException e) {
			System.out.println(e.getMessage());
			didWrite = false;
		}
		return didWrite;
	}
	
	public boolean deleteFlight(String flightNumber) {
		boolean didDelete;
		try {
			didDelete = deleteRecords(flightNumber);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());	
			didDelete = false;
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			didDelete = false;
		}
		catch(RecordNotFoundException e) {
			System.out.println(e.getMessage());
			didDelete = false;
		}
		return didDelete;
	}
	
	public Flight[] fetchAllFlights() {
		Flight[] flights = new Flight[]{};
		try {
			flights = getRecords();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flights;
	}
	
	// Gets all records in store
	public Flight[] getRecords() throws IOException, ClassNotFoundException, RecordNotFoundException {
		String filePath = "src/main/java/com/mycompany/frs_maven/data/flights.data";
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath));
		Flight[] flights = (Flight[]) input.readObject();
		input.close();
 		return flights;
	}
	
	// Clears store then adds records. For use in data loading
	// Use publishFlight in application
	public boolean addRecords(Flight[] data) throws IOException, ClassNotFoundException {
		boolean success = true;
		String filePath = "src/main/java/com/mycompany/frs_maven/data/flights.data";
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath));
		output.writeObject(data);
		output.flush();
		output.close();
		return success;	
	}
	
	private Flight getRecord(String flightNumber) throws IOException, ClassNotFoundException, RecordNotFoundException {
		Flight[] allRecords = getRecords();
		Flight requestedFlight = new Flight();
		if(allRecords.length > 0) {
			for(Flight f: allRecords) {
				String flightNum = f.getFlightNumber();
				if(flightNum.equals(flightNumber)) {
					requestedFlight = f;
				}
			}
		}
		if(requestedFlight.getFlightNumber() == null) {
			throw new RecordNotFoundException("No record found");
		}
		return requestedFlight;
	}
	
	private boolean addRecord(Flight data) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		Flight[] currentRecords = getRecords();
		int size = currentRecords.length + 1;
		Flight[] newRecords = new Flight[size];
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
	
	private boolean deleteRecords(String flightNumber) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		
		Flight[] currentRecords = getRecords();
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < currentRecords.length; i++) {
			String currFlightNumber = currentRecords[i].getFlightNumber();
			if(currFlightNumber.equals(flightNumber)) {
				indices.add(i);
			}
		}
		int size = currentRecords.length - indices.size();
		if(size > 0) {
			Flight[] newRecords = new Flight[size];
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
	
	public void printAllFlights() throws IOException, ClassNotFoundException, RecordNotFoundException {
		
		airports.put("BUR", "Burbank");
		airports.put("PSP", "Palm Springs");
		airports.put("BHM", "Birmingham International Airport");
		airports.put("DEN", "Denver International Airport");
		airports.put("ATL", "Atlanta Hartsfield Jackson International Airport");
		airports.put("ANC", "Anchorage International Airport");
		airports.put("SEA", "Seattle, Tacoma International Airport");
		airports.put("CRW", "Charleston");
		airports.put("IAD", "Washington, Dulles International Airport");
		airports.put("MLI", "Quad Cities International Airport");
		airports.put("JFK", "John F Kennedy International Airport");
		airports.put("BOM", "Chattrapathi Shivaji International Airport");
		
		airlines.put("AA", "American Airlines");
		airlines.put("AS", "Alaska Airlines");
		airlines.put("WN", "Southwest Airlines");
		airlines.put("DL", "Delta Airlines");
		airlines.put("AI", "Air India");
		
		Flight[] allFlights = getRecords();
		System.out.println("===All Flights===");
		System.out.println("");
		for(int i = 0; i < allFlights.length; i++) {
			Flight curr = allFlights[i];
			LocalDateTime cdt = curr.getDepartureTime();
			LocalDateTime cat = curr.getArrivalTime();
			System.out.println((i + 1) + ". " + airlines.get(curr.getAirlineCode()) + " Flight "  + curr.getFlightNumber());
			System.out.println("   " + airports.get(curr.getDepartureCode()) + " to " + airports.get(curr.getArrivalCode()));
			String dtm = String.valueOf(cdt.getMinute()).equals("0") ? "00" : String.valueOf(cdt.getMinute());
			String atm = String.valueOf(cat.getMinute()).equals("0") ? "00" : String.valueOf(cat.getMinute());
			String ddts = "   " + cdt.getDayOfWeek() + ", " + cdt.getMonth() + " " + cdt.getDayOfMonth() + " " + cdt.getHour() + ":" + dtm;
			String adts = "   " + cat.getDayOfWeek() + ", " + cdt.getMonth() + " " + cat.getDayOfMonth() + " " + cat.getHour() + ":" + atm;
			System.out.println("   Departure Time:" + ddts);
			System.out.println("   Arrival Time:" + adts);
			System.out.println("   Business Class Ticket: $" + curr.getbusinessTicket() + "0");
			System.out.println("   Economy Class Ticket: $" + curr.getEconomyTicket() + "0");
			System.out.println("");
		}
	}
	
	public Flight createFlight(String flightNumber, String airlineCode, String departureCode, LocalDateTime departureTime, 
			String arrivalCode, LocalDateTime arrivalTime, double businessTicket, double economyTicket) {
		Flight f = new Flight();
		f.setFlightNumber(flightNumber);
		f.setAirlineCode(airlineCode);
		f.setDepartureCode(departureCode);
		f.setDepartureTime(departureTime);
		f.setArrivalCode(arrivalCode);
		f.setArrivalTime(arrivalTime);
		f.setBusinessTicket(businessTicket);
		f.setEconomyTicket(economyTicket);
		return f;
	}
}
