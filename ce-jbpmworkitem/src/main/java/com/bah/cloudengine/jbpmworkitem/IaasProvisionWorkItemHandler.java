package com.bah.cloudengine.jbpmworkitem;


import com.bah.cloudengine.domain.ServiceOrder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

import javax.jms.*;


/**
 * IaaS provision handler with posts the single order message back to Servicemix
 * Active MQ
 */
public class IaasProvisionWorkItemHandler extends CloudEngineWorkItemHandler {

    public IaasProvisionWorkItemHandler(KieSession ksession) {
        this.kieSession = ksession;
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        logger.info("Entering IaasProvisionWorkItemHandler, executing ActiveMQ JMS WorkItem");

        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        boolean callServicemix = false;

        try {
            Object outObject = workItem.getParameter("OrderObject");
            String brokerURL = workItem.getParameter("BrokerURL").toString();
            String username = workItem.getParameter("username").toString();
            String password = workItem.getParameter("password").toString();
            String queue = workItem.getParameter("queue").toString();

            if (workItem.getParameter("servicemixResponseURL") != null) {
                this.servicemixResponseURL = workItem.getParameter("servicemixResponseURL").toString();
            }
            if (workItem.getParameter("orderProductId") != null) {
                this.orderProductId = workItem.getParameter("orderProductId").toString();
            }

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queue);

            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            ObjectMessage objectMessage = null;
            if (outObject instanceof ServiceOrder) {
                ServiceOrder serviceOrder = (ServiceOrder) outObject;
                callServicemix = false;
                logger.info("Invoking ActiveMQ with url: " + brokerURL + ", with ServiceOrder" + serviceOrder.toString());

                objectMessage = session.createObjectMessage(serviceOrder);
                messageProducer.send(objectMessage);
            } else {
                logger.error("Object not identified.");
            }

        } catch (JMSException jmse) {
            logger.error("JMS Exception POSTING to Active MQ:", jmse);
            this.abortAndHandleException(jmse, workItem, workItemManager, callServicemix);
        } catch (Exception e) {
            logger.error("Exception in posting :", e);
            this.abortAndHandleException(e, workItem, workItemManager, callServicemix);
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
                logger.error("JMS Exception closing resources:", e);
                this.abortAndHandleException(e, workItem, workItemManager);
            }
        }

        workItemManager.completeWorkItem(workItem.getId(), null);

    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        //not implemented
    }
}
