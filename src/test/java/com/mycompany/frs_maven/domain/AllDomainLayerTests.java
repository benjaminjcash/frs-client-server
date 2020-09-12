package com.mycompany.frs_maven.domain;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FlightTest.class, TravelerTest.class })
public class AllDomainLayerTests {
	
}
