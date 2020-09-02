package com.mycompany.frs_maven.service;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.domain.Flight;

public interface IFlightSvc extends IService {
	
	public final String NAME = "IFlightSvc";
	
	public Flight fetchFlight(String flightNumber);
	
	public boolean publishFlight(Flight flight);
	
	public boolean deleteFlight(String flightNumber);
	
	public ArrayList<Flight> fetchAllFlights();
	
	public boolean addRecords(ArrayList<Flight> flights) throws IOException, ClassNotFoundException;
	
	public ArrayList<Flight> getRecords() throws IOException, ClassNotFoundException, RecordNotFoundException;
	
	public void printAllFlights() throws IOException, ClassNotFoundException, RecordNotFoundException;
	
	public Flight createFlight(String flightNumber, String airlineCode, String departureCode, LocalDateTime departureTime, 
			String arrivalCode, LocalDateTime arrivalTime, double businessTicket, double economyTicket);
}
