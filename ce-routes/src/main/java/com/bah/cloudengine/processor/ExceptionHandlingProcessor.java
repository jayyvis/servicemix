package com.bah.cloudengine.processor;

import com.bah.cloudengine.domain.MIQEngineServiceOrderResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * This class handles exceptions thrown in the Servicemix ESB routes
 * and build the ServiceOrderResponse object so that ESB can send response back to the marketplace
 * and update the status accordingly with the description
 */
@Component("exceptionHandlingProcessor")
public class ExceptionHandlingProcessor implements Processor {

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * This method takes in the camel route message exchange when exception occurs
     * and builds ServiceOrderResponse object to send the response with failed status and descrption
     *
     * @param exchange The camel route message exchange
     */
    public void process(Exchange exchange) {
        Object messageBodyObj = exchange.getIn().getBody();

        //TODO: need to refactor based on new service order object with multiple products
        MIQEngineServiceOrderResponse orderResponse = new MIQEngineServiceOrderResponse();
        String orderProductId = null;

//        if (messageBodyObj instanceof ServiceOrder) {
//            ServiceOrder singleOrder = exchange.getIn().getBody(ServiceOrder.class);
//            orderProductId = String.valueOf(singleOrder.getInstances());
//        } else if (messageBodyObj instanceof InputStreamCache) {
//            String singleOrderStr = exchange.getIn().getBody(String.class);
//            try {
//                JsonNode root = new ObjectMapper().readTree(singleOrderStr);
//                JsonNode orderProductIdNode = root.get("order_product_id");
//                if (orderProductIdNode != null) {
//                    orderProductId = orderProductIdNode.asText().trim();
//                }
//            } catch (IOException ioe) {
//                logger.error("Exception while reading singleOrder json to extract order_product_id: ", ioe);
//            }
//        }
//        orderResponse.setOrderProductId(orderProductId);
//        orderResponse.setStatus("Failed");
//        Throwable fault = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
//        if (fault != null) {
//            orderResponse.setDescription(fault.getMessage());
//        }
//
//        exchange.getOut().setBody(orderResponse);
    }
}
