package com.bah.cloudengine.processor;

import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.MIQEngineServiceOrderResponse;
import com.bah.cloudengine.exception.MarketplaceOrderResponseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Enumeration;

/**
 * This class handles converting Java ServiceOrderResponse Object from/to JSON String representation
 * for the marketplace status post
 */
@Component("orderResponseHandler")
public class OrderResponseHandler {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private CloudEngineProperties properties;

    /**
     * Service Order Response *
     * Currently unused
     *
     * @param jsonOrderResponseStr
     * @return
     * @throws IOException
     * @throws
     */
    public MIQEngineServiceOrderResponse buildServiceOrderResponse(String jsonOrderResponseStr) throws IOException {

        logger.info("Received this Order response from MIQ: " + jsonOrderResponseStr);
        MIQEngineServiceOrderResponse MIQEngineServiceOrderResponse = new ObjectMapper().readValue(jsonOrderResponseStr, MIQEngineServiceOrderResponse.class);


        return MIQEngineServiceOrderResponse;
    }

    public String marketplaceJsonFactory(MIQEngineServiceOrderResponse miqEngineServiceOrderResponse) {
        String json = "";
        json = "{"+"\""+ miqEngineServiceOrderResponse.getItems().get(0).getMpuuid()+"\":"+"{"
                +"\"uuid\":\"" + miqEngineServiceOrderResponse.getItems().get(0).getMpuuid()+"\","
                +"\"status\":\"" + miqEngineServiceOrderResponse.getStatus()+"\","
                +"\"description\":\"" + miqEngineServiceOrderResponse.getDescription()+"\","
                +"\"fields\":{"
                +"\"cloudforms_guid\":\"" + miqEngineServiceOrderResponse.getItems().get(0).getMiqGuid()+"\","
                +"\"fqdn\":\"" + miqEngineServiceOrderResponse.getItems().get(0).getPublic_fqdn()+"\""
                +"}"
                +"}"
                +"}";



        return json;


    }




    public void postMarketplaceServiceOrderResponse(MIQEngineServiceOrderResponse miqEngineServiceOrderResponse) throws JsonProcessingException, MarketplaceOrderResponseException {
        //marketplace expects the order response(s) JSON to be wrapped with an array

//        UpdatedServiceOrderResponse updatedServiceOrderResponse = new UpdatedServiceOrderResponse();
//        OrderResponseItem orderResponseItem = new OrderResponseItem();
//
//        updatedServiceOrderResponse.setStatus(MIQEngineServiceOrderResponse.getStatus());
//        updatedServiceOrderResponse.setDescription(MIQEngineServiceOrderResponse.getDescription());
//        updatedServiceOrderResponse.setOrderId(MIQEngineServiceOrderResponse.getOrderId());
//        updatedServiceOrderResponse.setOrderProductId(MIQEngineServiceOrderResponse.getOrderProductId());
//        updatedServiceOrderResponse.setOrderProductType("vmware.single");
//        orderResponseItem.setItemName("Test Item");
//        orderResponseItem.setPublic_fqdn(MIQEngineServiceOrderResponse.getItems().get(0).getPublic_fqdn());
//        orderResponseItem.setHostname(MIQEngineServiceOrderResponse.getItems().get(0).getHostname());
//        orderResponseItem.setIpAddr(MIQEngineServiceOrderResponse.getItems().get(0).getIpAddr());
//
//        List<OrderResponseItem> updatedItems = new ArrayList<OrderResponseItem>();
//        updatedItems.add(orderResponseItem);
//        updatedServiceOrderResponse.setItems(updatedItems);
//
//        List orders = new ArrayList(0);
//        // orders.add(serviceOrderResponse);
//        orders.add(updatedServiceOrderResponse);
//
//        String jsonStr = new ObjectMapper().writeValueAsString(orders);

        String jsonStr = marketplaceJsonFactory(miqEngineServiceOrderResponse);
        logger.info("JSON to be sent to marketplace: " + jsonStr);

        try {
            //rest client to post message to marketplace
            Client client = Client.create();
            WebResource webResource = client.resource(properties.getMarketplaceRestURL());

            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("key", properties.getMarketplaceRestKey());
            formData.add("data", jsonStr);

            ClientResponse response = webResource.post(ClientResponse.class, formData);
            int httpStatusCode = response.getStatus();
            if (httpStatusCode == 401 || httpStatusCode == 404) {
                throw new MarketplaceOrderResponseException("Marketplace was unable to process the Service Order Response " +
                        "and sent the status code: " + httpStatusCode);
            }
            logger.info("Response from the marketplace for sending service order response: " + response.getEntity(String.class));

        } catch (Throwable e) {
            throw new MarketplaceOrderResponseException("Unable to post service order response to the marketplace REST endpoint.", e);
        }

    }

    /**
     * This method will pick up the order message(s) in a queue that was unsuccessful in posting to
     * the marketplace REST api to update the order status and re-attempt to send to the marketplace.
     * The method can be called if the cloudEngineToMarketPlace route runs successfully or can be manually
     * kicked-off from the REST endpoint /orderResponses/send/
     *
     * @throws JMSException                      if ActiveMQ has problem connecting to the message broker
     * @throws JsonProcessingException           if postMarketplaceOrderResponse throws exception while processing JSON conversion
     * @throws MarketplaceOrderResponseException if postMarketplaceOrderResponse throws exception while processing REST client
     */
    public void sendOrderResponseFromQueue() throws JMSException, JsonProcessingException, MarketplaceOrderResponseException {

        final String queueUser = properties.getActivemqUser();
        final String queuePassword = properties.getActivemqPassword();
        final String mqURL = properties.getActivemqBrokerURL();
        final String queueName = properties.getActivemqUnsuccessfulPostMarketplaceQueue();

        logger.debug("Invoking ActiveMQ to consume order responses from the queue name: " + queueName);

        Connection connection = null;
        Session session = null;
        MessageConsumer messageConsumer = null;
        QueueBrowser browser = null;

        try {
            //connect to activemq message broker
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(queueUser, queuePassword, mqURL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);

            browser = session.createBrowser(queue);
            Enumeration e = browser.getEnumeration();

            //finding queue size so we can loop through to get all messages
            int queueSizes = 0;
            while (e.hasMoreElements()) {
                queueSizes++;
                e.nextElement();
            }
            logger.debug("The queue message count is: " + queueSizes);

            //consuming all messages from the queue to send message to marketplace
            messageConsumer = session.createConsumer(queue);
            for (int i = 0; i < queueSizes; i++) {
                ObjectMessage message = (ObjectMessage) messageConsumer.receive();
                Object messageObject = message.getObject();

                if (messageObject != null && messageObject instanceof MIQEngineServiceOrderResponse) {
                    MIQEngineServiceOrderResponse response = (MIQEngineServiceOrderResponse) messageObject;
                    postMarketplaceServiceOrderResponse(response);
                    logger.debug("Method postMarketplaceServiceOrderResponse() invoked with: " + response.toString());
                }
            }
        } finally {
            try {
                if (messageConsumer != null) {
                    messageConsumer.close();
                }
                if (browser != null) {
                    browser.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                logger.error("JMS Exception closing resources in sendOrderResponseFromQueue:", e);
            }
        }

    }
}