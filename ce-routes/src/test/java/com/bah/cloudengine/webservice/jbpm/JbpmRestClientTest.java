package com.bah.cloudengine.webservice.jbpm;

import com.bah.cloudengine.common.CloudEngineProperties;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 *
 */
public class JbpmRestClientTest {

    @Mock
    private CloudEngineProperties properties;

    @InjectMocks
    private JbpmRestClient jbpmRestClient;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    //    @Test(expected = BPMExecutionException.class)
    public void testStartSingleOrderProcess() throws Exception {

        when(properties.getJbpmRestURL()).thenReturn("http://localhost:8080/business-central/");
        when(properties.getJbpmDeploymentID()).thenReturn("org.kie.example:project1:1.0.0-SNAPSHOT");
        when(properties.getJbpmProcessID()).thenReturn("project1.rules");
        when(properties.getJbpmUsername()).thenReturn("");
        when(properties.getJbpmPassword()).thenReturn("");

        when(properties.getActivemqBrokerURL()).thenReturn("tcp://0.0.0.0:61616");
        when(properties.getActivemqUser()).thenReturn("");
        when(properties.getActivemqPassword()).thenReturn("");
        when(properties.getActivemqIncomingQueue()).thenReturn("jbpmOrders");
        when(properties.getOrderResponseURL()).thenReturn("http://0.0.0.0:8183/orderResponses/create");

        String inOrderProductId = "1234";

        //Mockito.doNothing().when(jbpmRestClient).callBPMRestService("Test", "Test", "Test")  ;
        jbpmRestClient.startSingleOrderProcess(inOrderProductId);

    }

    //    @Test(expected = BPMExecutionException.class)
    public void testStartNagiosEnventMonitoringProcess() throws Exception {

        when(properties.getJbpmRestURL()).thenReturn("http://localhost:8080/business-central/");
        when(properties.getJbpmNagiosProcessDeploymentID()).thenReturn("org.kie.example:project1:1.0.0-SNAPSHOT");
        when(properties.getJbpmNagiosProcessProcessID()).thenReturn("project1.rules");
        when(properties.getJbpmUsername()).thenReturn("");
        when(properties.getJbpmPassword()).thenReturn("");

        when(properties.getActivemqBrokerURL()).thenReturn("tcp://0.0.0.0:61616");
        when(properties.getActivemqUser()).thenReturn("");
        when(properties.getActivemqPassword()).thenReturn("");
        when(properties.getActivemqNagiosEventQueue()).thenReturn("jbpmOrders");

        jbpmRestClient.startNagiosEnventMonitoringProcess();

    }

}
