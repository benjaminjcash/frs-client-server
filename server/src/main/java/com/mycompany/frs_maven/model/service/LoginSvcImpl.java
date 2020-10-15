package com.mycompany.frs_maven.model.service;

import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;

import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.exceptions.WrongPasswordException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.frs_maven.configuration.DIConfiguration;
import com.mycompany.frs_maven.model.business.TravelerMgr;
import com.mycompany.frs_maven.model.domain.Traveler;

public class LoginSvcImpl implements ILoginSvc  {
	public boolean login(String username, String password) throws RecordNotFoundException, WrongPasswordException, ServiceLoadException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		TravelerMgr travelerManager = context.getBean(TravelerMgr.class);
		
		Traveler profile = travelerManager.fetchProfile(username);
		if(profile == null) {
			context.close();
			throw new RecordNotFoundException("No profile exists with that username");
		}
		
		String fetchedPassword = profile.getPassword();
		boolean authenticated;
		if(fetchedPassword.equals(password)) {
			authenticated = true;
		} else {
			context.close();
			throw new WrongPasswordException("The password you provided is incorrect");
		}
		context.close();
		return authenticated;
	}
}
