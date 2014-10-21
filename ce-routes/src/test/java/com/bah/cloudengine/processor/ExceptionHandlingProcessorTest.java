package com.bah.cloudengine.processor;

import org.apache.camel.test.junit4.CamelTestSupport;

/**
 *
 */
public class ExceptionHandlingProcessorTest extends CamelTestSupport {

//    @EndpointInject(uri = "mock:result")
//    protected MockEndpoint resultEndpoint;
//
//    @Produce(uri = "direct:start")
//    protected ProducerTemplate template;
//
//    @Test
//    public void testProcessWithServiceOrder() throws Exception {
//        ServiceOrder serviceOrder = new ServiceOrder();
//        serviceOrder.setCustomerFirstName("JBPM");
//        serviceOrder.setOrderId(112233);
//        serviceOrder.setOrderProductId(12345678);
//        serviceOrder.setTemplateName("fedora_temp");
//
//        serviceOrder.setCustomerEmail("park_john@bah.com");
//        serviceOrder.setCustomerLastName("JBPMLast");
//        serviceOrder.setVmName("jbpmtest_" + new Date().getTime());
//        serviceOrder.setHostName("esx01");
//        serviceOrder.setOs("linux");
//
//
//        ServiceOrderResponse response = new ServiceOrderResponse();
//        response.setOrderProductId("12345678");
//        response.setStatus("Failed");
//
//        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
//        mockEndpoint.expectedMessageCount(1);
//
//        template.sendBody(serviceOrder);
//
//        Thread.sleep(1000);
//
//        mockEndpoint.assertIsSatisfied();
//
//    }
//
//    @Test
//    public void testProcessWithJsonString() throws Exception {
//        URL url = getClass().getResource("/orderResponseTest.json");
//        File file = new File(url.getPath());
//
//        byte[] encoded = Files.readAllBytes(Paths.get(url.toURI()));
//
//        String jsonStr = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
//
//        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
//        mockEndpoint.expectedMessageCount(1);
//
//        template.sendBody(jsonStr);
//
//        Thread.sleep(1000);
//
//        mockEndpoint.assertIsSatisfied();
//    }
//
//    @Override
//    protected RouteBuilder createRouteBuilder() throws Exception {
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                ExceptionHandlingProcessor exceptionHandlingProcessor = new ExceptionHandlingProcessor();
//
//                from("direct:start")
//                        .process(exceptionHandlingProcessor)
//                        .to("mock:result");
//            }
//        };
//    }

}
