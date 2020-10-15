package com.mycompany.frs_maven.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mycompany.frs_maven.model.service.IFlightSvc;
import com.mycompany.frs_maven.model.service.ILoginSvc;
import com.mycompany.frs_maven.model.service.ITravelerSvc;
import com.mycompany.frs_maven.model.service.LoginSvcImpl;
import com.mycompany.frs_maven.model.service.hibernate_svc.FlightSvcHibernateImpl;
import com.mycompany.frs_maven.model.service.hibernate_svc.TravelerSvcHibernateImpl;

@Configuration
@ComponentScan(value={"com.mycompany.frs_maven.model.business"})
public class DIConfiguration {
	
	@Bean
	public IFlightSvc getFlightService() {
		return new FlightSvcHibernateImpl();
	}
	
	@Bean
	public ITravelerSvc getTravelerService() {
		return new TravelerSvcHibernateImpl();
	}
	
	@Bean
	public ILoginSvc getLoginSvc() {
		return new LoginSvcImpl();
	}
}
