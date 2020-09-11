package com.mycompany.frs_maven.service;

import java.time.LocalDateTime;

import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.domain.Flight;

public class DatabaseHydratorUtility {
	static private Logger logger = LogManager.getLogger();
	static private IFlightSvc flightSvc;
	
	public static void main(String[] args) {
		Factory factory = Factory.getInstance();
		
		// Flights
		Flight f1 = new Flight();
		f1.setFlightNumber("B601");
		f1.setAirlineCode("B6"); //FlightBlue
		f1.setDepartureCode("MAA"); //Chennai International, Chennai, India
		f1.setArrivalCode("MAD"); //Adolfo Suárez Madrid–Barajas, Madrid, Spain
		f1.setDepartureTime(LocalDateTime.of(2020, Month.AUGUST, 20, 16, 30, 00));
		f1.setArrivalTime(LocalDateTime.of(2020, Month.AUGUST, 20, 23, 30, 00));
		f1.setEconomyTicket(1680);
		f1.setBusinessTicket(2200);
		
		Flight f2 = new Flight();
		f2.setFlightNumber("JL16");
		f2.setAirlineCode("JL"); //Japan Airlines
		f2.setDepartureCode("KIX"); //Kansai International Airport, Japan
		f2.setArrivalCode("NRT"); //Narita International Airport, Japan
		f2.setDepartureTime(LocalDateTime.of(2020, Month.MAY, 3, 2, 45, 00));
		f2.setArrivalTime(LocalDateTime.of(2020, Month.MAY, 3, 5, 30, 00));
		f2.setEconomyTicket(200);
		f2.setBusinessTicket(380);
		
		Flight f3 = new Flight();
		f3.setFlightNumber("UA99");
		f3.setAirlineCode("UA"); //United Airlines
		f3.setDepartureCode("BUR"); //Hollywood Burbank Airport, California, US
		f3.setArrivalCode("EYE"); //Eagle Creek Airport, Indiana, US
		f3.setDepartureTime(LocalDateTime.of(2020, Month.DECEMBER, 11, 14, 00, 00));
		f3.setArrivalTime(LocalDateTime.of(2020, Month.DECEMBER, 11, 19, 45, 00));
		f3.setEconomyTicket(350);
		f3.setBusinessTicket(425);
		
		try {
			flightSvc = (IFlightSvc) factory.getService(IFlightSvc.NAME);
			flightSvc.publishFlight(f1);
			flightSvc.publishFlight(f2);
			flightSvc.publishFlight(f3);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
