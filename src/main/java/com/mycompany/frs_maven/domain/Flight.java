package com.mycompany.frs_maven.domain;

import java.io.Serializable;

import java.time.LocalDateTime;

public class Flight implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Flight() {}
	
	/* Properties */
	private String flightNumber;
	private String airlineCode;
	private String departureCode;
	private LocalDateTime departureTime;
	private String arrivalCode;
	private LocalDateTime arrivalTime;
	private double businessTicket;
	private double economyTicket;
	
	/* Getters and Setters */
	public String getFlightNumber() {
		return this.flightNumber;
	}
	public void setFlightNumber(String num) {
		this.flightNumber = num;
	}
	
	public String getAirlineCode() {
		return this.airlineCode;
	}
	public void setAirlineCode(String code) {
		this.airlineCode = code;
	}
	
	public String getDepartureCode() {
		return this.departureCode;
	}
	public void setDepartureCode(String code) {
		this.departureCode = code;
	}
	
	public LocalDateTime getDepartureTime() {
		return this.departureTime;
	}
	public void setDepartureTime(LocalDateTime time) {
		this.departureTime = time;
	}
	
	public String getArrivalCode() {
		return this.arrivalCode;
	}
	public void setArrivalCode(String code) {
		this.arrivalCode = code;
	}
	
	public LocalDateTime getArrivalTime() {
		return this.arrivalTime;
	}
	public void setArrivalTime(LocalDateTime time) {
		this.arrivalTime = time;
	}
	
	public double getbusinessTicket() {
		return this.businessTicket;
	}
	public void setBusinessTicket(double cost) {
		this.businessTicket = cost;
	}
	
	public double getEconomyTicket() {
		return this.economyTicket;
	}
	public void setEconomyTicket(double cost) {
		this.economyTicket = cost;
	}
	
	/* Equals */
	public boolean equals(Flight flight) {
		String fn = flight.getFlightNumber();
		if(fn.equals(this.flightNumber)) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Validate */
	public boolean validate() {
		boolean valid = true;
		
		if(this.airlineCode == null || this.flightNumber == null || this.departureCode == null || this.departureTime == null ||
			this.arrivalCode == null || this.arrivalTime == null) {
			valid = false;
		}
		
		if(this.flightNumber.length() != 3) {
			valid = false;
		}
		
		return valid;
	}
}
