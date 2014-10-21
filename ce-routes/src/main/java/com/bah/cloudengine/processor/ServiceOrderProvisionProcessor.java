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
 * Created on:
 * Date: 8/15/14
 * Time: 3:16 PM
 */
@Component("ServiceOrderProvisionProcessor")
public class ServiceOrderProvisionProcessor implements Processor {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Resource
    MiqRestClient miqRestClient;
    @Resource
    CloudEngineProperties properties;

    @Override
    public void process(Exchange exchange) throws Exception {

        String jsonBody = exchange.getIn().getBody(String.class);
        ServiceOrder serviceOrder = new ObjectMapper().readValue(jsonBody, ServiceOrder.class);

        PostOrderServiceCatalogTemplate postOrderServiceCatalogTemplate = new PostOrderServiceCatalogTemplate();
        postOrderServiceCatalogTemplate.setAction("order");
        ServiceTemplateResource serviceTemplateResource = new ServiceTemplateResource();


        for (ServiceOrderInstance instance : serviceOrder.getInstances())

        {
            serviceTemplateResource.setHref(properties.getRestHrefBaseUrl() + String.valueOf(instance.getServiceTemplateId()));
            serviceTemplateResource.setServicemix_url(serviceOrder.getServicemixUrl());
            serviceTemplateResource.setOrder_id(String.valueOf(serviceOrder.getOrderId()));
            serviceTemplateResource.setOrder_product_id(instance.getOrderProductId());
            serviceTemplateResource.setMpuuid(instance.getMpuuid());
            serviceTemplateResource.setChef_url(serviceOrder.getChefUrl());
            serviceTemplateResource.setChef_client_name(serviceOrder.getChefClientName());
            serviceTemplateResource.setChef_signing_key_filename(serviceOrder.getChefSigningKeyFilename());
            serviceTemplateResource.setAws_access_key_id(serviceOrder.getAwsAccessKeyId());
            serviceTemplateResource.setAws_secret_access_key(serviceOrder.getAwsSecretAccessKey());
            serviceTemplateResource.setOrder_id(Long.toString(serviceOrder.getOrderId()));
            serviceTemplateResource.setTermination_date(serviceOrder.getTerminationDate());
            serviceTemplateResource.setApprover(serviceOrder.getApproverFirstName() + serviceOrder.getApproverLastName());
            serviceTemplateResource.setNte_budget(serviceOrder.getNteYearBudget());
            serviceTemplateResource.setCc(serviceOrder.getChargeCode());
            serviceTemplateResource.setRequester_full_name(serviceOrder.getRequesterFirstName() + serviceOrder.getApproverLastName());
            serviceTemplateResource.setChef_roles(instance.getChef_roles());

            serviceTemplateResource.setTags(instance.getTags());
            postOrderServiceCatalogTemplate.setResource(serviceTemplateResource);
            ServiceOrderRequest serviceOrderRequest = miqRestClient.miqProvisionServiceTemplate(instance.getServiceCatalogIdAsInt(), postOrderServiceCatalogTemplate);
            instance.setServiceOrderRequest(serviceOrderRequest);


        }


        exchange.getIn().setBody(serviceOrder);


        exchange.setOut(exchange.getIn());

    }


    /**
     * This method checks SingleOrder object to validate mandatory data is set.  If not, it throws
     * CloudFormsProvisionRequestException to prevent the SOAP message to be sent to CF.
     *
     * @param exchange SingleOrder object which contains the marketplace order request data
     * @throws com.bah.cloudengine.exception.MIQProvisionRequestException if singleOrder is missing mandatory fields that CloudForms expects
     */
//    public static void validateProvisionRequestInput(ServiceOrder singleOrder) throws CloudFormsProvisionRequestException {
//
//        for (int i = 0; i < singleOrder.getInstances().size(); i++) {
//            ServiceOrderInstance product = singleOrder.getInstances().get(i);
//            if (StringUtils.isBlank(product.getVmName())) {
//                throw new CloudFormsProvisionRequestException("VM name missing.  Aborting order request: "
//                        + product.getOrderProductId()); //Return product ID or Order ID?
//            }
//
//
////        if (StringUtils.isBlank(singleOrder.getTemplateName()) && StringUtils.isBlank(singleOrder.getTemplateGuid())) {
////            throw new CloudFormsProvisionRequestException("VM template name and template GUID missing. " +
////                    "Aborting order request: " + singleOrder.getOrderProductId());
////        }
//
//            if (StringUtils.isBlank(singleOrder.getRequesterEmail())) {
//                throw new CloudFormsProvisionRequestException("Missing requester email address. " +
//                        "Aborting order request: " + singleOrder.getOrderId());
//            }
//
//            // Is the customer the requester in this case?
//            if (StringUtils.isBlank(singleOrder.getRequesterFirstName())
//                    || StringUtils.isBlank(singleOrder.getRequesterLastName())) {
//                throw new CloudFormsProvisionRequestException("Missing requester first or last name. " +
//                        "Aborting order request: " + singleOrder.getOrderId());
//            }
//        }
//    }
}
