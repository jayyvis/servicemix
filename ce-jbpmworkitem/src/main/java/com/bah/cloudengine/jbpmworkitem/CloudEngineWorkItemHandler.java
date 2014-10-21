package com.bah.cloudengine.jbpmworkitem;

import com.bah.cloudengine.domain.MIQEngineServiceOrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import javax.ws.rs.core.MediaType;

/**
 * Common cloud engine work item handler to manage exception handling
 */
public abstract class CloudEngineWorkItemHandler implements WorkItemHandler {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected KieSession kieSession;
    protected String servicemixResponseURL = "";
    protected String orderProductId = "";
    String username = "";
    String password = "";

    /**
     * This method calls the servicemix end point to communicate the fault message
     * in case of an error.
     *
     * @param message message to be sent to servicemix
     */
    protected void handleFault(String message) {
        Client client = Client.create();

        MIQEngineServiceOrderResponse response = new MIQEngineServiceOrderResponse();
        response.setOrderProductId(this.orderProductId);
        response.setStatus("Failed");
        response.setDescription("BPM Server: " + message);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            logger.error("Exception parsing ServiceOrderResponse object to JSON.", e);
        }
        logger.info("JSON String: " + jsonStr);

        //WebResource webResource = client.resource("http://10.200.78.115:8183/orderResponses/create");
        WebResource webResource = client.resource(servicemixResponseURL);
        client.addFilter(new HTTPBasicAuthFilter(this.username, this.password));
        ClientResponse restResponse = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, jsonStr);

        logger.info("Got response from servicemix with status: " + restResponse.getStatus());
    }

    /**
     * Aborts the process and sends the message.
     *
     * @param cause           exception cause
     * @param workItem        Current work item
     * @param workItemManager current work item manager
     */
    protected void abortAndHandleException(Throwable cause, WorkItem workItem, WorkItemManager workItemManager) {
        logger.info("Invoking abort and handle exception.Call Servicemix to send failed message");

        abortAndHandleException(cause, workItem, workItemManager, true);

    }

    protected void abortAndHandleException(Throwable cause, WorkItem workItem, WorkItemManager workItemManager,
                                           boolean callServicemix) {
        if (kieSession != null) {
            kieSession.abortProcessInstance(workItem.getProcessInstanceId());
        }

        workItemManager.abortWorkItem(workItem.getId());

        if (callServicemix) {
            handleFault(ExceptionUtils.getRootCauseMessage(cause));
        }
    }

}
