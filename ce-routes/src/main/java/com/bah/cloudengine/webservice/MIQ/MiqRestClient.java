package com.bah.cloudengine.webservice.MIQ;

import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehta on 7/7/2014.
 */

@Component("miqRestClient")
public class MiqRestClient {

    final Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private CloudEngineProperties properties;


    public ServiceOrderRequest miqProvisionServiceTemplate(Integer service_catalog_id, PostOrderServiceCatalogTemplate postOrder) throws RuntimeException, IOException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        WebResource webResource = miqBaseWebResource;
        StringWriter jsonRequestBody = new StringWriter();
        jsonObjectMapper.writeValue(jsonRequestBody, postOrder);

        logger.info("Posting following JSON data: " + jsonRequestBody);

        ClientResponse response = webResource.path("service_catalogs").path(service_catalog_id.toString()).path("service_templates").post(ClientResponse.class, jsonRequestBody.toString());
        logger.info("Posting to URL: " + webResource.getURI().getPath());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed MIQ Rest API Call : HTTP error code : " + response.getStatus() + "\n Response:" + response.getEntity(String.class));
        }

        String responseString = response.getEntity(String.class);
        logger.info("Got following JSON response : " + responseString);
        ServiceOrderRequestResponse serviceOrderRequestResponse = jsonObjectMapper.readValue(responseString, ServiceOrderRequestResponse.class);
        jsonRequestBody.close();
        logger.info("Returning result object");
        serviceOrderRequestResponse.getResults().get(0).setRequest_id(Integer.valueOf(serviceOrderRequestResponse.getResults().get(0).getId()));
        return serviceOrderRequestResponse.getResults().get(0);


    }


    public ServiceOrderRequest miqUpdateRequestStatus(Integer request_id) throws IOException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;
        String jsonResponseString = webResource.path("requests").path(request_id.toString()).get(String.class);
        ServiceOrderRequest serviceOrderRequest = jsonObjectMapper.readValue(jsonResponseString, ServiceOrderRequest.class);

        logger.info("Request ID: " + serviceOrderRequest.getId() + " has status of: " + serviceOrderRequest.getRequest_state() + " with message: " + serviceOrderRequest.getMessage());
        return serviceOrderRequest;
        //return requestState
    }


    public MiqVM miqServiceRequestVmInfo(Integer request_id) throws IOException, InterruptedException {
        Integer task_id = miqRequestTaskId(request_id);
        logger.info("Wating for 20 seconds for destination id to show up");
        Thread.sleep(20000);
        Integer vmId = miqGetVmIdFromTask(task_id);
        Thread.sleep(1000);
        return miqGetVmInfo(vmId);
    }

    private Integer miqRequestTaskId(Integer request_id) throws IOException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;
        String paramValue = "miq_request_id=" + request_id.toString();

        logger.info("Using Param Value " + paramValue);
        String jsonResponseString = webResource.path("request_tasks").queryParam("sqlfilter", paramValue).get(String.class);
        JsonNode rootNode = jsonObjectMapper.readTree(jsonResponseString);
        JsonNode hrefNodes = rootNode.path("resources");
        logger.info("JSON response: " + jsonResponseString);
        logger.debug("====================");
        Integer task_id = -100;
        for (JsonNode hrefNode : hrefNodes) {
            String href = hrefNode.path("href").asText();
            String[] segments = href.split("/");
            if (Integer.valueOf(segments[segments.length - 1]) > task_id) {
                task_id = Integer.valueOf(segments[segments.length - 1]);
                logger.info("Larger Task ID found: " + task_id.toString());
            }
        }
        return task_id;
    }


    private Integer miqGetVmIdFromTask(Integer task_id) throws IOException, InterruptedException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;

        logger.info("in miq get vmid with task id :" + task_id.toString());
        String phase = "";
        Integer count = 0;
        JsonNode destinationIdNode = MissingNode.getInstance();
        while (!"finish".equals(phase) && destinationIdNode.isMissingNode()) {
            String jsonResponseString = webResource.path("request_tasks").path(task_id.toString()).get(String.class);
            logger.info("got response from request task: " + jsonResponseString);
            JsonNode rootNode = jsonObjectMapper.readTree(jsonResponseString);
            destinationIdNode = rootNode.path("destination_id");
            JsonNode phaseNode = rootNode.path("phase");
            phase = phaseNode.asText();
            Thread.sleep(5000);
            count++;
        }
        logger.info("vmid = : " + destinationIdNode.asText());
        Integer vmId = destinationIdNode.asInt();
        return vmId;
    }

    public MiqVM miqGetVmInfo(Integer vmId) throws IOException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;
        String jsonResponseString = webResource.path("vms").path(String.valueOf(vmId)).get(String.class);
        logger.info(jsonResponseString);
        MiqVM miqVM = jsonObjectMapper.readValue(jsonResponseString, MiqVM.class);
        logger.info("VM Info : " + jsonResponseString);
        return miqVM;
    }

    //Implementation of Sandbox Code
    public List<Integer> miqGetServiceVms(Integer request_id) throws IOException, InterruptedException {

        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqClient.resource(miqApiBaseURL + "request_tasks?expand=resources&sqlfilter=miq_request_id=" + request_id.toString());
        ClientResponse clientResponse = webResource.get(ClientResponse.class);
        String jsonResponseString = clientResponse.getEntity(String.class);
        logger.debug("=====================");
        logger.info(jsonResponseString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponseString);
        JsonNode resourcesNode = rootNode.path("resources");
        System.out.println(resourcesNode.toString());
        List<Integer> serviceVmsList = new ArrayList<Integer>();
        for (JsonNode node : resourcesNode) {
            if (node.path("destination_type").asText().equals("VmOrTemplate"))
                serviceVmsList.add(node.path("destination_id").asInt());

        }
        return serviceVmsList;
    }

    // Sets up the tagging
    public void miqSetVMTag(Integer vmId, ArrayList<PostVmTag.resource> tagsList) throws IOException {


        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        //accept tags and vm id
        WebResource webResource = miqBaseWebResource;
        PostVmTag postVmTag = new PostVmTag();
        postVmTag.setAction("assign");
        postVmTag.setResources(tagsList);
        StringWriter stringWriter = new StringWriter();
        jsonObjectMapper.writeValue(stringWriter, postVmTag);
        String jsonPostBody = stringWriter.toString();
        logger.info("Posting following JSON data: " + jsonPostBody);

        ClientResponse response = webResource.path("vms").path(String.valueOf(vmId)).path("tags").post(ClientResponse.class, jsonPostBody);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed MIQ Rest API Call : HTTP error code : " + response.getStatus() + "\n Response:" + response.getEntity(String.class));
        }

        String responseString = response.getEntity(String.class);
        logger.info("Got following JSON response : " + responseString);


        //return status or tags
    }

    public void miqRemoveVMTag(Integer vmId, ArrayList<PostVmTag.resource> tagsList) throws IOException {
        //accept tags and vm id


        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;
        PostVmTag postVmTag = new PostVmTag();
        postVmTag.setAction("unassign");
        postVmTag.setResources(tagsList);
        StringWriter stringWriter = new StringWriter();
        jsonObjectMapper.writeValue(stringWriter, postVmTag);
        String jsonPostBody = stringWriter.toString();
        logger.info("Posting following JSON data: " + jsonPostBody);

        ClientResponse response = webResource.path("services").path(String.valueOf(vmId)).path("tags").post(ClientResponse.class, jsonPostBody);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed MIQ Rest API Call : HTTP error code : " + response.getStatus() + "\n Response:" + response.getEntity(String.class));
        }

        String responseString = response.getEntity(String.class);
        logger.info("Got following JSON response : " + responseString);
    }


    public ServiceOrderRequest miqTerminateService(PostOrderServiceCatalogTemplate postOrderServiceCatalogTemplate, Integer id) throws RuntimeException, IOException {


        String miqApiBaseURL = properties.getRestBaseUrl();
        String miqApiRestUsername = properties.getRestUser();
        String miqApiRestPassword = properties.getRestPassword();
        Client miqClient = Client.create();
        WebResource miqBaseWebResource = miqClient.resource(miqApiBaseURL);
        miqClient.addFilter(new HTTPBasicAuthFilter(miqApiRestUsername, miqApiRestPassword));
        miqBaseWebResource.type("application/json");
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);


        WebResource webResource = miqBaseWebResource;
        StringWriter jsonRequestBody = new StringWriter();
        jsonObjectMapper.writeValue(jsonRequestBody, postOrderServiceCatalogTemplate);
        logger.info("Posting to URL: " + (webResource.path("services").path(id.toString())).toString());

        logger.info("Posting following JSON data: " + jsonRequestBody);

        ClientResponse response = webResource.path("services").path(id.toString()).post(ClientResponse.class, jsonRequestBody.toString());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed MIQ Rest API Call: HTTP error code: " + response.getStatus() + "\n Response:" + response.getEntity(String.class));
        }

        String responseString = response.getEntity(String.class);
        logger.info("Got following JSON response: " + responseString);
        jsonRequestBody.close();
        logger.info("Service Terminated");
        ServiceOrderRequestResponse serviceOrderRequestResponse = jsonObjectMapper.readValue(responseString, ServiceOrderRequestResponse.class);
        return serviceOrderRequestResponse.getResults().get(0);
    }


}
