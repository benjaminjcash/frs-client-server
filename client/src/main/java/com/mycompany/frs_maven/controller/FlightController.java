package com.mycompany.frs_maven.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mycompany.frs_maven.model.domain.*;

public class FlightController {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Flight> fetchAllFlights(){
		ArrayList<Flight> fetchedFlights = new ArrayList<Flight>();
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("fetchAllFlights");
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		Object data = dtoIn.getData();
		fetchedFlights = (ArrayList<Flight>)data;
		return fetchedFlights;
	}
}
