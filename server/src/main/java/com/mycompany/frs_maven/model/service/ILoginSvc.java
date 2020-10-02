package com.mycompany.frs_maven.model.service;

import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.exceptions.WrongPasswordException;

public interface ILoginSvc extends IService {
	
	public final String NAME = "ILoginSvc";
	
	public boolean login(String username, String password) throws  RecordNotFoundException, WrongPasswordException, ServiceLoadException;
}
