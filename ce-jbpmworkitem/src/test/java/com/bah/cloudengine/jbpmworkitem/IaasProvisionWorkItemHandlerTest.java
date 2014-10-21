package com.bah.cloudengine.jbpmworkitem;


import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.After;
import org.junit.Before;

/**
 * JUnit Test for <code>IaasProvisionWorkItemHandler</code> using jBPM runtime
 * manager
 */
public class IaasProvisionWorkItemHandlerTest extends JbpmJUnitBaseTestCase {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    //    @Test
    public void testExecuteWorkItem() throws Exception {
//
//        // create runtime manager with single process
//        createRuntimeManager("iaasProvisionTest.bpmn2");
//
//        // take RuntimeManager to work with process engine
//        RuntimeEngine runtimeEngine = getRuntimeEngine();
//
//        // get access to KieSession instance
//        KieSession kSession = runtimeEngine.getKieSession();
//
//        kSession.getWorkItemManager().registerWorkItemHandler("Service Task", new org.jbpm.bpmn2.handler.ServiceTaskHandler());
//
//        kSession.getWorkItemManager().registerWorkItemHandler("IaasProvision", new com.bah.cloudengine.jbpmworkitem.IaasProvisionWorkItemHandler(kSession));
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        // initialize variables here if necessary
//        String brokerURL = "tcp://localhost:61616";
//        String activemqUsername = "admin";
//        String activemqPassword = "admin";
//        String queue = "provisionRequests";
//        ServiceOrder order = new ServiceOrder();
//        order.setOrderId(5);
//
//        params.put("inBrokerURL", brokerURL);
//        params.put("inUsername", activemqUsername);
//        params.put("inPassword", activemqPassword);
//        params.put("inOutQueue", queue);
//        params.put("singleOrder", order);
//
//        // start process
//        ProcessInstance processInstance = kSession.startProcess("cetest.iaasProvisionTest", params);
//
//        // check whether the process instance has completed successfully
//        assertProcessInstanceCompleted(processInstance.getId(), kSession);
//
//        // check what nodes have been triggered
//        assertNodeTriggered(processInstance.getId(), "Start", "IaasProvision", "End");

    }

}
