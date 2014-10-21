package com.bah.cloudengine.processor;

import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.ServiceOrder;
import com.bah.cloudengine.domain.ServiceOrderInstance;
import com.bah.cloudengine.webservice.MIQ.MiqRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ServiceOrderProvisionProcessorTest extends CamelTestSupport {


    private final Logger logger = Logger.getLogger(this.getClass());
    @EndpointInject(uri = "mock:results")
    protected MockEndpoint resultEndpoint;
    @Produce(uri = "direct:start")
    protected ProducerTemplate template;
    @Mock
    private MiqRestClient client;
    @Mock
    private CloudEngineProperties properties;
    @InjectMocks
    private ServiceOrderProvisionProcessor serviceOrderProvisionProcessor;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcess() throws Exception {

        ServiceOrder order = new ServiceOrder();
        ServiceOrderInstance product = new ServiceOrderInstance();
        List<ServiceOrderInstance> plist = new ArrayList<ServiceOrderInstance>();

        order.setOrderId(1234L);
        order.setRequesterFirstName("John");
        order.setRequesterLastName("Smith");
        order.setRequesterEmail("vargas_raymond@bah.com");
        order.setRequesterUuid("222222");
        order.setTeam("DPI");
        order.setProject("");
        order.setTerminationDate("2014/10/09");
        order.setApproverFirstName("first");
        order.setApproverLastName("last");
        order.setApproverEmailAddress("vargas_raymond@bah.com");
        order.setApproverUuid("000000");
        order.setChargeCode("1234A00000");
        order.setNteYearBudget("12341234.00");
        order.setOrderStatus("requested");


        product.setOrderProductId("213");
        product.setMpuuid("12343");
        product.setServiceCatalogId("1");
        product.setServiceTemplateId("2");
        product.setOrderServiceType("miq.service.single");
        product.setVmName("name");
        product.setStatus("requested");

        plist.add(product);
        order.setInstances(plist);

        when(properties.getRestBaseUrl()).thenReturn("http://54.164.73.99:3000/api/");
        when(properties.getRestUser()).thenReturn("admin");
        when(properties.getRestPassword()).thenReturn("smartvm");
        when(properties.getRestClientBaseUrl()).thenReturn("http://ec2-54-88-29-213.compute-1.amazonaws.com:3000/api/");
        when(properties.getRestHrefBaseUrl()).thenReturn("http://ec2-54-88-29-213.compute-1.amazonaws.com:3000/api/service_templates/");


        String jsonBody = new ObjectMapper().writeValueAsString(order);
        logger.info(jsonBody);

        template.sendBody(jsonBody);
        resultEndpoint.expectedMessageCount(0);
        resultEndpoint.assertIsSatisfied();

    }


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                serviceOrderProvisionProcessor = new ServiceOrderProvisionProcessor();

                from("direct:start")
                        .process(serviceOrderProvisionProcessor)
                        .to("mock:result");


            }
        };
    }

}