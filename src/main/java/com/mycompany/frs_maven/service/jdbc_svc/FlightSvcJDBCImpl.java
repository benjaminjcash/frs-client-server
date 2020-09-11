package com.mycompany.frs_maven.service.jdbc_svc;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.domain.Flight;
import com.mycompany.frs_maven.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.service.IFlightSvc;

public class FlightSvcJDBCImpl implements IFlightSvc {
	
	static private Logger logger = LogManager.getLogger();
	private Connection connection = null;
	private String url = "jdbc:mysql://localhost:3306/frs_db";
	private String userId = "root";
	private String password = "admin";
	
	private void fetchConnection() {
		try {
			connection = DriverManager.getConnection(url, userId, password);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public Flight fetchFlight(String flightNumber) {
		Flight flight = new Flight();
		fetchConnection();
		if(connection != null) {
			try {
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM flights WHERE flightNumber = '" + flightNumber + "'";
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					flight.setFlightNumber(resultSet.getString("flightNumber"));
					flight.setAirlineCode(resultSet.getString("airlineCode"));
					flight.setDepartureCode(resultSet.getString("departureCode"));
					flight.setArrivalCode(resultSet.getString("arrivalCode"));
					flight.setDepartureTime(resultSet.getObject("departureTime", LocalDateTime.class));
					flight.setArrivalTime(resultSet.getObject("arrivalTime", LocalDateTime.class));
					flight.setEconomyTicket(resultSet.getDouble("economyTicket"));
					flight.setBusinessTicket(resultSet.getDouble("businessTicket"));
				}
				statement.close();
				connection.close();
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		} else {
			logger.info("no connection to database");
		}
		return flight;
	}

	public boolean publishFlight(Flight flight) {
		Boolean success = false;
		fetchConnection();
		if(connection != null) {
			try {
				Statement statement = connection.createStatement();
				String sql = "INSERT INTO flights (flightNumber, airlineCode, departureCode, arrivalCode, departureTime, arrivalTime, economyTicket, businessTicket) VALUES ("
					+ "'" + flight.getFlightNumber() + "', "
					+ "'" + flight.getAirlineCode() + "', "
					+ "'" + flight.getDepartureCode() + "', "
					+ "'" + flight.getArrivalCode() + "', "
					+ "'" + flight.getDepartureTime() + "', "
					+ "'" + flight.getArrivalTime() + "', "
					+ "'" + flight.getEconomyTicket() + "', "
					+ "'" + flight.getBusinessTicket() + "')";
				statement.executeUpdate(sql);
				statement.close();
				connection.close();
				success = true;
			}
			catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		} else {
			logger.info("no connection to database");
		}
		return success;
	}

	public boolean deleteFlight(String flightNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Flight> fetchAllFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	public void printAllFlights() throws IOException, ClassNotFoundException, RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
}
