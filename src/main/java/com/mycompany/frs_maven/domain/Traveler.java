package com.mycompany.frs_maven.domain;

import java.io.Serializable;

public class Traveler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Traveler() {}
	
	/* Properties */
	private String name;
	private String address;
	private String username; // unique identifier in db
	private String password;
	private String creditCardNumber;
	private String expirationDate;
	
	/* Getters and Setters */
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}
	public void setCreditCardNumber(String ccNumber) {
		this.creditCardNumber = ccNumber;
	}
	
	public String getExpirationDate() {
		return this.expirationDate;
	}
	public void setExpirationDate(String date) {
		this.expirationDate = date;
	}
	
	/* Equals */
	public boolean equals(Traveler traveler) {
		boolean equals = true;
		
		if(traveler.getName() != this.name || traveler.getUsername() != this.username) {
			equals = false;
		}
		
		return equals;
	}
	
	/* Validate */
	public boolean validate() {
		boolean valid = true;
		
		if(this.name == null || this.address == null || this.username == null || this.password == null) {
			valid = false;
		}
		
		return valid;
	}
}
