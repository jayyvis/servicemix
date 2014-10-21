package com.bah.cloudengine.common;

import com.bah.cloudengine.exception.CloudEngineRoutingException;
import com.bah.cloudengine.exception.MIQProvisionRequestException;
import com.bah.cloudengine.exception.MarketplaceOrderResponseException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * This class contains the CloudEngine Route definitions for catalog
 * orders
 */
@Component("serviceCatalogOrderRoute")
public class ServiceCatalogOrderRoute extends RouteBuilder {

    @Resource
    private CloudEngineProperties properties;

    @Override
    public void configure() throws Exception {
//        onException(Exception.class)
//                .processRef("exceptionHandlingProcessor")
//                .to("direct:cloudEngineToMarketPlace");

        //marketplaceToIncoming
        from("jetty:http://0.0.0.0:8183/orders/create/")
                .routeId("marketplaceToIncomingServiceOrder")
                .onException(CloudEngineRoutingException.class).handled(true)
                .transform(constant("Input validation error."))
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500)).end()
                .inOnly("activemq:provisionRequests");

        //jbpmRestCall
//        int maxRedeliveriesBPM = properties.getServicemixToBpmMaximumRedeliveries();
//        long delayBPM = properties.getServicemixToBpmRedeliveryDelay();
//
//        from("activemq:incomingServiceOrders")
//                .routeId("jbpmServiceRestCall")
//                .onException(BPMExecutionException.class)
//                .maximumRedeliveries(maxRedeliveriesBPM).redeliveryDelay(delayBPM)
//                .processRef("exceptionHandlingProcessor")
//                .to("direct:cloudEngineToMarketPlace").end()
//                .processRef("marketplaceJsonOrderProcessor")
//                .to("mock:end");


        //MIQ redelivery attempt config
        int miqMaxRedeliveries = properties.getServicemixToBpmMaximumRedeliveries();
        long miqRedeliveryDelay = properties.getServicemixToBpmRedeliveryDelay();
        //brmsToMiq
        from("activemq:provisionRequests")
                .routeId("brmsToMiqServiceOrder")
                .onException(MIQProvisionRequestException.class)
                .maximumRedeliveries(miqMaxRedeliveries).redeliveryDelay(miqRedeliveryDelay)
                .processRef("exceptionHandlingProcessor")
                .to("direct:cloudEngineToMarketPlace").end()
                .log("Message content: ${body}")
                .processRef("ServiceOrderProvisionProcessor")
                .to("mock:end");


        //orderResponseEndPoint
        from("jetty:http://0.0.0.0:8183/orderResponses/create/")
                .routeId("serviceOrderResponsesEndPoint")
                .log("HTTP End Point for processing Order Response: ${body}")
                .to("bean:orderResponseHandler?method=buildServiceOrderResponse")
                .to("direct:cloudEngineToMarketPlace");
//        .to("mock:end");

        //ceToMarketPlace
        from("direct:cloudEngineToMarketPlace")
                .routeId("serviceCloudEngineToMarketPlace")
                .onException(MarketplaceOrderResponseException.class)
                .inOnly("activemq:unsuccessfulPostMarketplace").end()
                .to("bean:orderResponseHandler?method=postMarketplaceServiceOrderResponse")
                .to("bean:orderResponseHandler?method=sendOrderResponseFromQueue");

        //manual kickoff endpoint for sending marketplace order responses in queue
        from("jetty:http://0.0.0.0:8183/orderResponses/send/")
                .routeId("sendServiceOrderResponsesEndPoint")
                .log("HTTP End Point for sending Order Response to marketplace from queue")
                .to("bean:orderResponseHandler?method=sendOrderResponseFromQueue");

    }
}
