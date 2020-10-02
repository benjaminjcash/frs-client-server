package com.mycompany.frs_maven.model.business;

import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;
import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.exceptions.WrongPasswordException;
import com.mycompany.frs_maven.model.service.Factory;
import com.mycompany.frs_maven.model.service.ILoginSvc;

public class LoginMgr {
	private ILoginSvc loginSvc;
	private void setup() throws ServiceLoadException {
		Factory factory = Factory.getInstance();
		loginSvc = (ILoginSvc) factory.getService(ILoginSvc.NAME);
	}
	
	public boolean login(String username, String password) throws RecordNotFoundException, WrongPasswordException, ServiceLoadException {
		setup();
		return loginSvc.login(username, password);
	}
}
 