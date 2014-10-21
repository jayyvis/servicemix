package com.bah.cloudengine.webservice.jbpm;


import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.exception.JBPMExecutionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component("jbpmRestClient")
public class JbpmRestClient {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private CloudEngineProperties properties;

    /**
     * Rest call to start BPM process
     *
     * @param orderProductId
     * @throws Exception
     */
    public void startSingleOrderProcess(String orderProductId) throws UnsupportedEncodingException, JBPMExecutionException {

        String jbpmRestURL = properties.getJbpmRestURL();
        String deploymentID = properties.getJbpmDeploymentID();
        String processID = properties.getJbpmProcessID();
        String restUsername = properties.getJbpmUsername();
        String restPassword = properties.getJbpmPassword();

        String startProcessURL = jbpmRestURL + "rest/runtime/" + deploymentID + "/process/" + processID + "/start?";
        logger.info("Invoking BPM Start Process URL :" + startProcessURL);

        HashMap<String, String> urlHashmap = new HashMap<String, String>();

        urlHashmap.put("glbBrokerURL", properties.getActivemqBrokerURL());
        urlHashmap.put("glbUsername", properties.getActivemqUser());
        urlHashmap.put("glbPassword", properties.getActivemqPassword());
        urlHashmap.put("glbIncomingQueue", properties.getActivemqIncomingQueue());
        urlHashmap.put("glbOrderProductId", orderProductId);
        urlHashmap.put("glbServicemixResponseURL", properties.getOrderResponseURL());

        startProcessURL += varmapToURLString(urlHashmap);
        //Call BPM
        callBPMRestService(startProcessURL, restUsername, restPassword);

    }

    /**
     * Method to call Nagios Even monitoring process
     *
     * @throws UnsupportedEncodingException
     * @throws com.bah.cloudengine.exception.JBPMExecutionException
     */
    public void startNagiosEnventMonitoringProcess()
            throws UnsupportedEncodingException, JBPMExecutionException {

        String jbpmRestURL = properties.getJbpmRestURL();
        String deploymentID = properties.getJbpmNagiosProcessDeploymentID();
        String processID = properties.getJbpmNagiosProcessProcessID();
        String restUsername = properties.getJbpmUsername();
        String restPassword = properties.getJbpmPassword();

        String startProcessURL = jbpmRestURL + "rest/runtime/" + deploymentID + "/process/" + processID + "/start?";
        logger.info("Invoking " + processID + " Start Process URL :" + startProcessURL);

        HashMap<String, String> urlHashmap = new HashMap<String, String>();

        urlHashmap.put("glbBrokerURL", properties.getActivemqBrokerURL());
        urlHashmap.put("glbUsername", properties.getActivemqUser());
        urlHashmap.put("glbPassword", properties.getActivemqPassword());
        urlHashmap.put("glbIncomingQueue", properties.getActivemqNagiosEventQueue());

        startProcessURL += varmapToURLString(urlHashmap);

        //Call BPM
        callBPMRestService(startProcessURL, restUsername, restPassword);

    }

    /**
     * Utility method to invoke BPM Rest API
     *
     * @param startProcessURL
     * @param restUsername
     * @param restPassword
     * @throws com.bah.cloudengine.exception.JBPMExecutionException
     */
    private void callBPMRestService(String startProcessURL, String restUsername, String restPassword)
            throws JBPMExecutionException {
        logger.debug("Invoking BPM with URL: " + startProcessURL);
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(startProcessURL);
            client.addFilter(new HTTPBasicAuthFilter(restUsername, restPassword));
            ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, "");
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            logger.debug(response.getEntity(String.class).toString());
        } catch (RuntimeException e) {
            logger.error("Exception calling BPM rest client.", e);
            throw new JBPMExecutionException("Exception calling BPM rest client.", e);
        }
    }

    /**
     * Utility method to generate parameter string to BPM rest call
     *
     * @param hashMap
     * @return
     * @throws Exception
     */
    public String varmapToURLString(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        String outputStr = "";
        Iterator entries = hashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            outputStr += "map_" + key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
        }
        return outputStr;
    }

}
