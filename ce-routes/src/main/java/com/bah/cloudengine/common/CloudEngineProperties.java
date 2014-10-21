package com.bah.cloudengine.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class contains the CloudEngine properties that reads in attributes
 * from com.bah.cloudengine.cfg properties file in servicemix ESB
 */
@Component("cloudEngineProperties")
public class CloudEngineProperties {

    private final Logger logger = Logger.getLogger(this.getClass());
    //activemq properties
    @Value("${activemq.user}")
    private String activemqUser;
    @Value("${activemq.password}")
    private String activemqPassword;
    @Value("${activemq.brokerURL}")
    private String activemqBrokerURL;
    @Value("${activemq.incomingQueue}")
    private String activemqIncomingQueue;
    @Value("${activemq.unsuccessfulPostMarketplaceQueue}")
    private String activemqUnsuccessfulPostMarketplaceQueue;
    @Value("${servicemix.orderResponseURL}")
    private String orderResponseURL;
    @Value("${activemq.nagiosEventsQueue}")
    private String activemqNagiosEventQueue;
    //Miq rest api properties
    @Value("${rest.baseUrl}")
    private String restBaseUrl;
    @Value("${rest.user}")
    private String restUser;
    @Value("${rest.password}")
    private String restPassword;
    @Value("${rest.clientBaseUrl}")
    private String restClientBaseUrl;
    @Value("${rest.hrefBase}")
    private String restHrefBaseUrl;
    //jbpm properties
    @Value("${jbpm.restURL}")
    private String jbpmRestURL;
    @Value("${jbpm.deploymentID}")
    private String jbpmDeploymentID;
    @Value("${jbpm.processID}")
    private String jbpmProcessID;
    @Value("${jbpm.username}")
    private String jbpmUsername;
    @Value("${jbpm.password}")
    private String jbpmPassword;
    @Value("${jbpm.nagiosprocess.deploymentID}")
    private String jbpmNagiosProcessDeploymentID;
    @Value("${jbpm.nagiosprocess.processID}")
    private String jbpmNagiosProcessProcessID;
    //marketplace properties
    @Value("${marketplace.restURL}")
    private String marketplaceRestURL;
    @Value("${marketplace.restKey}")
    private String marketplaceRestKey;
    @Value("${servicemix.bpm.maximumRedeliveries}")
    private String servicemixToBpmMaximumRedeliveries;
    @Value("${servicemix.bpm.redeliveryDelay}")
    private String servicemixToBpmRedeliveryDelay;
    @Value("${servicemix.miq.maximumRedeliveries}")
    private String servicemixToMiqMaximumRedeliveries;
    @Value("${servicemix.miq.redeliveryDelay}")
    private String servicemixToMiqRedeliveryDelay;

    public String getActivemqUser() {
        return activemqUser;
    }

    public String getActivemqPassword() {
        return activemqPassword;
    }

    public String getActivemqBrokerURL() {
        return activemqBrokerURL;
    }

    public String getActivemqIncomingQueue() {
        return activemqIncomingQueue;
    }

    public String getActivemqUnsuccessfulPostMarketplaceQueue() {
        return activemqUnsuccessfulPostMarketplaceQueue;
    }

    public String getJbpmRestURL() {
        return jbpmRestURL;
    }

    public String getJbpmDeploymentID() {
        return jbpmDeploymentID;
    }

    public String getJbpmProcessID() {
        return jbpmProcessID;
    }

    public String getJbpmUsername() {
        return jbpmUsername;
    }

    public String getJbpmPassword() {
        return jbpmPassword;
    }

    public String getMarketplaceRestURL() {
        return marketplaceRestURL;
    }

    public String getMarketplaceRestKey() {
        return marketplaceRestKey;
    }

    public String getOrderResponseURL() {
        return orderResponseURL;
    }

    public String getRestBaseUrl() {
        return restBaseUrl;
    }

    public String getRestUser() {
        return restUser;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public String getRestClientBaseUrl() {
        return restClientBaseUrl;
    }

    public String getRestHrefBaseUrl() {
        return restHrefBaseUrl;
    }

    public int getServicemixToBpmMaximumRedeliveries() {
        int value = 0;
        try {
            value = Integer.parseInt(servicemixToBpmMaximumRedeliveries);
        } catch (NumberFormatException e) {
            logger.error("The value of servicemix.bpm.maximumRedeliveries in the properties file has to be a number. Defaulting to 0.", e);
        }
        return value;
    }

    public long getServicemixToBpmRedeliveryDelay() {
        long value = 0;
        try {
            value = Long.parseLong(servicemixToBpmRedeliveryDelay);
        } catch (NumberFormatException e) {
            logger.error("The value of servicemix.bpm.redeliveryDelay in the properties file has to be a number. Defaulting to 0.", e);
        }
        return value;
    }

    public int getServicemixToMiqMaximumRedeliveries() {
        int value = 0;
        try {
            value = Integer.parseInt(servicemixToMiqMaximumRedeliveries);
        } catch (NumberFormatException e) {
            logger.error("The value of servicemix.miq.maximumRedeliveries in the properties file has to be a number. Defaulting to 0.", e);
        }
        return value;
    }

    public long getServicemixToMiqRedeliveryDelay() {
        long value = 0;
        try {
            value = Long.parseLong(servicemixToMiqRedeliveryDelay);
        } catch (NumberFormatException e) {
            logger.error("The value of servicemix.miq.redeliveryDelay in the properties file has to be a number. Defaulting to 0.", e);
        }
        return value;
    }

    public String getActivemqNagiosEventQueue() {
        return activemqNagiosEventQueue;
    }

    public String getJbpmNagiosProcessDeploymentID() {
        return jbpmNagiosProcessDeploymentID;
    }

    public String getJbpmNagiosProcessProcessID() {
        return jbpmNagiosProcessProcessID;
    }

}