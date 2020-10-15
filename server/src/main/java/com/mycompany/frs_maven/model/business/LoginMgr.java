package com.mycompany.frs_maven.model.business;

import com.mycompany.frs_maven.model.exceptions.RecordNotFoundException;

import com.mycompany.frs_maven.model.exceptions.ServiceLoadException;
import com.mycompany.frs_maven.model.exceptions.WrongPasswordException;
import com.mycompany.frs_maven.model.service.ILoginSvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginMgr {
	private ILoginSvc loginSvc;
	
	@Autowired
	public void setLoginService(ILoginSvc loginSvc) {
		this.loginSvc = loginSvc;
	}
	
	public synchronized boolean login(String username, String password) throws RecordNotFoundException, WrongPasswordException, ServiceLoadException {
		return this.loginSvc.login(username, password);
	}
}
 