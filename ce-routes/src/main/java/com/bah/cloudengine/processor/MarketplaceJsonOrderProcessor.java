package com.bah.cloudengine.processor;


import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.ServiceOrder;
import com.bah.cloudengine.webservice.jbpm.JbpmRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component("marketplaceJsonOrderProcessor")
public class MarketplaceJsonOrderProcessor implements Processor {

    static ObjectMapper mapper = new ObjectMapper();
    static ProducerTemplate producerTemplate;
    private final Logger logger = Logger.getLogger(this.getClass());
    @Resource
    JbpmRestClient jbpmRestClient;
    @Resource
    private CloudEngineProperties properties;

    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
//        logger.info("In the marketplace Json Processor");
//        Object orderObj = exchange.getIn().getBody();
//
//        if (orderObj == null) {
//            logger.error("SingleOrder object is null");
//            throw new CloudEngineRoutingException("SingleOrder object cannot be null");
//        }
//
//        //Connect to BPM server an check if the BPM is up and running
//        try {
//            logger.info("Checking the BPM server connectivity");
//            String bpmURL = properties.getJbpmRestURL();
//            HttpURLConnection httpConnection = (HttpURLConnection) new URL(bpmURL).openConnection();
//            httpConnection.setRequestMethod("HEAD");
//            int responseCode = httpConnection.getResponseCode();
//            if (responseCode != 200) {
//                logger.error("Unable to connect to BPM server.");
//                throw new BPMExecutionException("Unable to connect to BPM server.");
//            }
//        } catch (Throwable e) {
//            throw new BPMExecutionException("Unable to connect to BPM server.");
//        }
//        //Post the Message to queue and then call the BPM server
//        logger.info("Successfully connected to BPM server proceding to send message to queue and start BPM process.");
//
//        //TODO need to find a better pattern
//        if (orderObj instanceof ServiceOrder) {
//            ServiceOrder serviceOrder = (ServiceOrder) orderObj;
//
//            postSingleOrderMessage(serviceOrder);
//
//            //Call BPM to continue the process
//            //String inOrderProductId = String.valueOf(serviceOrder.getOrderProductId());
//
//            //Call the BPM rest client
//            jbpmRestClient.startSingleOrderProcess(inOrderProductId);
//
//        } else {
//            logger.info("Object not identified.");
//        }
//
//        exchange.setOut(exchange.getIn());

    }

    /**
     * Utility method to post the <code>SingleOrder</code> to queue
     *
     * @param serviceOrder
     * @throws JMSException
     */
    protected void postSingleOrderMessage(ServiceOrder serviceOrder) throws JMSException {

        String mqURL = properties.getActivemqBrokerURL();
        String queueName = properties.getActivemqIncomingQueue();
        String queueUser = properties.getActivemqUser();
        String queuePasswd = properties.getActivemqPassword();

        logger.debug("Invoking AtiveMQ to post the SingeOrder to :" + queueName);

        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(queueUser, queuePasswd, mqURL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            ObjectMessage objectMessage = session.createObjectMessage(serviceOrder);
            messageProducer.send(objectMessage);
        } finally {
            try {
                if (messageProducer != null) {
                    messageProducer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                logger.error("JMS Exception closing resources in postSingleOrderMessage:", e);
            }
        }

    }

}
