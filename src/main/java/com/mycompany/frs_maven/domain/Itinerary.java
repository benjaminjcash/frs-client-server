package com.mycompany.frs_maven.domain; 

import java.io.Serializable;

public class Itinerary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Itinerary() {}
	
	/* Properties */
	private String id;
	private Double totalCost;
	private Flight[] flights;
	private String status;
	
	/* Getters and Setters */
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public double getTotalCost() {
		return this.totalCost;
	}
	public void setTotalCost(Double total) {
		this.totalCost = total;
	}
	
	public Flight[] getFlights() {
		return this.flights;
	}
	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/* Equals */
	public boolean equals(Itinerary itinerary) {
		boolean equals = true;
		
		String id = itinerary.getId();
		if(!id.equals(this.id)) {
			equals = false;
		}
		
		return equals;
	}
	
	/* Validate */
	public boolean validate() {
		boolean valid = true;
		
		if(this.id == null || this.totalCost == null || this.flights == null || this.status == null) {
			valid = false;
		}
		
		return valid;
	}
}
