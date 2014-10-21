package com.bah.cloudengine.processor;

import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.*;
import com.bah.cloudengine.webservice.MIQ.MiqRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Vargas
 * Date: 8/18/14
 * Time: 9:22 AM
 */
@Component("ServiceOrderProvisionTerminateProcessor")
public class ServiceOrderProvisionTerminateProcessor implements Processor {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private CloudEngineProperties properties;

    @Override
    public void process(Exchange exchange) throws Exception {
        MiqRestClient miqRestClient = new MiqRestClient();

        String jsonBody = exchange.getIn().getBody(String.class);
        ServiceOrder serviceOrder = new ObjectMapper().readValue(jsonBody, ServiceOrder.class);

        PostOrderServiceCatalogTemplate postOrderServiceCatalogTemplate = new PostOrderServiceCatalogTemplate();
        postOrderServiceCatalogTemplate.setAction("retire");
        ServiceTemplateResource serviceTemplateResource = new ServiceTemplateResource();

        for (ServiceOrderInstance product : serviceOrder.getInstances()) {

            serviceTemplateResource.setHref(properties.getRestHrefBaseUrl() + String.valueOf(product.getServiceCatalogIdAsInt()));
            postOrderServiceCatalogTemplate.setResource(serviceTemplateResource);

            ServiceOrderRequest serviceOrderRequest = miqRestClient.miqTerminateService(postOrderServiceCatalogTemplate, product.getServiceCatalogIdAsInt());
            product.setServiceOrderRequest(serviceOrderRequest);
        }
        exchange.getIn().setBody(serviceOrder);

        exchange.setOut(exchange.getIn());


    }


}
