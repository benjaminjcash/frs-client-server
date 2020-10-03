package com.mycompany.frs_maven.controller;

import java.util.ArrayList;

import com.mycompany.frs_maven.model.domain.DTO;
import com.mycompany.frs_maven.model.domain.Traveler;

public class TravelerController {
	@SuppressWarnings("unchecked")
	public ArrayList<Traveler> fetchAllProfiles(){
		ArrayList<Traveler> fetchedProfiles = new ArrayList<Traveler>();
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("fetchAllProfiles");
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		Object data = dtoIn.getData();
		fetchedProfiles = (ArrayList<Traveler>)data;
		return fetchedProfiles;
	}
	
	public Traveler fetchProfile(String username) {
		Traveler fetchedProfile = new Traveler();
		DTO dtoOut = new DTO();
		Traveler traveler = new Traveler();
		traveler.setUsername(username);
		dtoOut.setData(traveler);
		dtoOut.setCommandString("fetchProfile");
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		fetchedProfile = (Traveler)dtoIn.getData();
		return fetchedProfile;
	}
	
	public Boolean createProfile(Traveler traveler) {
		Boolean status = false;
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("createProfile");
		dtoOut.setData(traveler);
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		status = (Boolean)dtoIn.getStatus();
		return status;
	}
	
	public Boolean deleteProfile(String username) {
		Boolean status = false;
		DTO dtoOut = new DTO();
		dtoOut.setCommandString("deleteProfile");
		Traveler traveler = new Traveler();
		traveler.setUsername(username);
		dtoOut.setData(traveler);
		FlightReservationSystemController controller = new FlightReservationSystemController();
		DTO dtoIn = controller.performAction(dtoOut);
		status = (Boolean)dtoIn.getStatus();
		return status;
	}
}
