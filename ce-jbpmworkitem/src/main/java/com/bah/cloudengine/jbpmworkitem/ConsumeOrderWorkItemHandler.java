package com.bah.cloudengine.jbpmworkitem;

import com.bah.cloudengine.domain.ServiceOrder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Consume single order handler with consumes the order object from Servicemix
 * Active MQ
 */
public class ConsumeOrderWorkItemHandler extends CloudEngineWorkItemHandler {

    public ConsumeOrderWorkItemHandler(KieSession ksession) {
        this.kieSession = ksession;
    }

    /**
     * Execute work item
     *
     * @param workItem
     * @param workItemManager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {

        logger.info("Entering ConsumeOrderWorkItemHandler, reading message from ActiveMQ");

        //SingleOrder singleOrder = new SingleOrder();
        Object orderObj = null;
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            String brokerURL = workItem.getParameter("brokerURL").toString();
            this.username = workItem.getParameter("username").toString();
            this.password = workItem.getParameter("password").toString();
            String incomingQueue = workItem.getParameter("incomingQueue").toString();
            this.servicemixResponseURL = workItem.getParameter("servicemixResponseURL").toString();
            this.orderProductId = workItem.getParameter("orderProductId").toString();

            logger.info("Invoking ActiveMQ with url: " + brokerURL + ", with Queue: " + incomingQueue +
                    "Product ID: " + this.orderProductId);

            //Get the message object from Active MQ
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(incomingQueue);

            consumer = session.createConsumer(destination);

            Message message = consumer.receive(30000);

            if (message instanceof ObjectMessage) {
                orderObj = ((ObjectMessage) message).getObject();

                if (orderObj instanceof ServiceOrder) {
                    ServiceOrder serviceOrder = (ServiceOrder) orderObj;
                    logger.info("Grabbed Service Order from Queue: " + serviceOrder.toString());
                } else {
                    logger.error("Object not identified.");
                }

            } else {
                logger.error("Received unknown message: " + message);
                this.handleFault("Received unknown message:" + message.toString());
            }
        } catch (JMSException jmse) {
            logger.error("JMS Exception consuming SingleOrder from Active MQ:", jmse);
            this.abortAndHandleException(jmse, workItem, workItemManager);
        } catch (Exception e) {
            logger.error("Exception in consume SingleOrder:", e);
            this.abortAndHandleException(e, workItem, workItemManager);
        } finally {
            try {
                if (consumer != null) {
                    consumer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                logger.error("JMS Exception closing resources:", e);
                this.abortAndHandleException(e, workItem, workItemManager);
            }

        }

        Map<String, Object> outMap = new HashMap<String, Object>();
        outMap.put("singleOrderOut", orderObj);

        workItemManager.completeWorkItem(workItem.getId(), outMap);

    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        //Not Implemented yet
    }
}
