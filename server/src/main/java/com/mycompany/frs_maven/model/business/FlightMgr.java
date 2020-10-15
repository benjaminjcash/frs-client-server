package com.mycompany.frs_maven.model.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.frs_maven.model.service.IFlightSvc;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.model.domain.Flight;
import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;

@Component
public class FlightMgr {
	static private Logger logger = LogManager.getLogger();
	private IFlightSvc flightSvc;
	
	@Autowired
	public void setFlightService(IFlightSvc flightSvc) {
		this.flightSvc = flightSvc;
	}
	
	public synchronized Flight fetchFlight(String flightNumber) throws ServiceLoadException {
		return this.flightSvc.fetchFlight(flightNumber);
	}
	
	public synchronized boolean publishFlight(Flight flight) throws ServiceLoadException {
		return this.flightSvc.publishFlight(flight);
	}
	
	public synchronized boolean deleteFlight(String flightNumber) throws ServiceLoadException {
		return this.flightSvc.deleteFlight(flightNumber);
	}
	
	public synchronized boolean updateFlight(Flight flight) throws RecordNotFoundException {
		return this.flightSvc.updateFlight(flight);
	}
	
	public synchronized ArrayList<Flight> fetchAllFlights() throws ServiceLoadException {
		return this.flightSvc.fetchAllFlights();
	}
	
	public synchronized void printAllFlights() throws RecordNotFoundException, ClassNotFoundException, IOException {
		this.flightSvc.printAllFlights();
	}
	
	public List<Flight> searchForFlights(String departureCode, LocalDateTime departureTime, Integer numberOfPassengers) {		
		List<Flight> flights = new ArrayList<>();
		
		try {
			flights = this.flightSvc.fetchAllFlights();
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		// Filter by departure code
		List<Flight> temp = new ArrayList<>();
		for(int i = 0; i < flights.size(); i++) {
			Flight curr = flights.get(i);
			String dc = curr.getDepartureCode();
			
			if(departureCode.equals(dc)) {
				temp.add(curr);
				String code = flights.get(i).getFlightNumber();
				logger.info("Codes matched for flight " + code);
			}
		}
		flights = temp;
		
		// Filter by departure time (match day)
		List<Flight> temp2 = new ArrayList<>();
		for(int i = 0; i < flights.size(); i++) {
			Flight curr = flights.get(i);
			LocalDateTime dt = curr.getDepartureTime();
			LocalDate d = dt.toLocalDate();
			LocalDate departureDate = departureTime.toLocalDate();
			
			if(d.equals(departureDate)) {
				temp2.add(curr);
				String code = flights.get(i).getFlightNumber(); 
				logger.info("Codes matched for flight " + code);
			}
		}
		flights = temp2;
		
		return flights;
	}
	
}
