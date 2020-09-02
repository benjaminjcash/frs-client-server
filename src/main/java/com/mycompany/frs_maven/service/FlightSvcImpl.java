package com.mycompany.frs_maven.service;


import java.time.LocalDateTime;
import java.util.ArrayList;

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
		// print data
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
