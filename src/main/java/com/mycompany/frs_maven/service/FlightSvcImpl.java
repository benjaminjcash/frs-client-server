package com.mycompany.frs_maven.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.domain.Flight;

public class FlightSvcImpl implements IFlightSvc {

	static private Logger logger = LogManager.getLogger();
	
	public Flight fetchFlight(String flightNumber) {
		Flight flight = new Flight();
		try {
			flight = getRecord(flightNumber);
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
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
		return flight;
	}

	
	public boolean publishFlight(Flight flight) {
		boolean didWrite;
		try {
			didWrite = addRecord(flight);
		}
		catch(IOException e) {
			logger.error(e.getMessage());	
			didWrite = false;
		}
		catch(ClassNotFoundException e) {
			logger.error(e.getMessage());
			didWrite = false;
		}
		catch(RecordNotFoundException e) {
			logger.error(e.getMessage());
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
	
	public ArrayList<Flight> fetchAllFlights() {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try {
			flights = getRecords();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		return flights;
	}
	
	public ArrayList<Flight> getRecords() throws IOException, ClassNotFoundException, RecordNotFoundException {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		// retrieve data
 		return flights;
	}
	
	public boolean addRecords(ArrayList<Flight> data) throws IOException, ClassNotFoundException {
		boolean success = true;
		// write data
		return success;	
	}
	
	private Flight getRecord(String flightNumber) throws IOException, ClassNotFoundException, RecordNotFoundException {
		Flight requestedFlight = new Flight();
		// retrieve data
		return requestedFlight;
	}
	
	private boolean addRecord(Flight data) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		// write data
		return success;
	}
	
	private boolean deleteRecords(String flightNumber) throws IOException, ClassNotFoundException, RecordNotFoundException {
		boolean success = true;
		// delete data
		return success;
	}
	
	public void printAllFlights() throws IOException, ClassNotFoundException, RecordNotFoundException {
		ArrayList<Flight> allFlights = this.fetchAllFlights();
		
		// define lambda expression
		StringBuilder builder = (String label, String value) -> {
			String str = label + ": " + value;
			return str;
		};
		
		// build list of flight numbers
		ArrayList<String> flightNumbers = new ArrayList<String>();
		for(int i = 0; i < allFlights.size(); i++) {
			Flight flight = allFlights.get(i);
			flightNumbers.add(flight.getFlightNumber());
		}
		
		// sort list
		Stream<String> stream = flightNumbers.stream().sorted();
		List<String> list = stream.collect(Collectors.toList());
		
		// print list
		for(int i = 0; i < list.size(); i++) {
			String label = (i + 1) + ". ";
			System.out.println(builder.build(label, list.get(i)));
		}
	}
}
