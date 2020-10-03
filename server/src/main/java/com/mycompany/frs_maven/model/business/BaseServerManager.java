package com.mycompany.frs_maven.model.business;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.frs_maven.model.domain.DTO;
import com.mycompany.frs_maven.model.service.ServicesManager;

public abstract class BaseServerManager {
	static private Logger logger = LogManager.getLogger();
	public abstract DTO performAction(DTO dto);
	
	static {
		try {
			BaseServerManager.loadServices();
		}
		catch(Exception e) {
			logger.error(e);
		}
	}
	
	public static void loadServices() throws Exception {
		String servicesFileLocation = System.getProperty("services_location");
		if(servicesFileLocation != null) {
			ServicesManager.loadServices(servicesFileLocation);
		} else {
			logger.error("Services file location not set. Passed in value is: " + servicesFileLocation + ".");
			throw new Exception();
		}
	}
}