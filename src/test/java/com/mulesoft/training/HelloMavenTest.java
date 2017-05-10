package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.mule.tck.junit4.rule.DynamicPort;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;


public class HelloMavenTest extends FunctionalTestCase {
	
	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");

    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.print("\n\n Dynamic Port ------> " + dynamicPort.getNumber() + "\n\n");
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
    @Override
    protected String getConfigFile() {
        return "maven-project.xml";
    }

    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
    	System.out.print("\n\n Dynamic Port ------> " + dynamicPort.getNumber() + "\n\n");
      MuleEvent event = runFlow("retrieveFlights");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
}
