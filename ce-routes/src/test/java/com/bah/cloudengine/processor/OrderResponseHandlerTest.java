package com.bah.cloudengine.processor;

import org.apache.camel.test.junit4.CamelTestSupport;

public class OrderResponseHandlerTest extends CamelTestSupport {

//    @EndpointInject(uri = "mock:result")
//    protected MockEndpoint mockResult;
//
//    @Produce(uri = "direct:orderResponseEndPoint")
//    protected ProducerTemplate orderResponseEndPoint;
//
//    @Before
//    public void testSetup() {
//    }
//
//    //    @Test(expected = CamelExecutionException.class)
//    public void testInvalidOrderProductId() throws Exception {
//        ServiceOrderResponse orderResponse = new ServiceOrderResponse();
//
//        //order product id has to be a number -- this should fail
//        orderResponse.setOrderProductId("ssss");
//        orderResponse.setStatus("failed");
//        orderResponse.setDescription("error in acquiring ip");
//
//
//        String badOrderResponseJson = new ObjectMapper().writeValueAsString(orderResponse);
//
//        //expecting this route to fail due to inputValidationException
//        orderResponseEndPoint.sendBody(badOrderResponseJson);
//    }
//
//    //    @Test(expected = CamelExecutionException.class)
//    public void testNullOrderProductId() throws Exception {
//
//        ServiceOrderResponse orderResponse = new ServiceOrderResponse();
//        //order product id has to be in the json
//        orderResponse.setOrderProductId(null);
//        orderResponse.setStatus("failed");
//        orderResponse.setDescription("error in acquiring ip");
//
//
//        String badOrderResponseJson = new ObjectMapper().writeValueAsString(orderResponse);
//
//        orderResponseEndPoint.sendBody(badOrderResponseJson);
//    }
//
//    @Test
//    public void testGoodOrderResponse() throws Exception {
//
//        ServiceOrderResponse orderResponse = new ServiceOrderResponse();
//        orderResponse.setOrderProductId("1234556");
//        orderResponse.setStatus("failed");
//        orderResponse.setDescription("error in acquiring ip");
//
//
//        String orderResponseJson = new ObjectMapper().writeValueAsString(orderResponse);
//
//        mockResult.expectedMessageCount(2);
//        orderResponseEndPoint.sendBody(orderResponseJson);
//        orderResponseEndPoint.sendBody(orderResponseJson);
//
//        mockResult.assertIsSatisfied();
//    }
//
//    @Test(expected = MarketplaceOrderResponseException.class)
//    public void testUnsuccessfulPostMarketplaceOrderResponse() throws Exception {
//
//        ServiceOrderResponse orderResponse = new ServiceOrderResponse();
//        orderResponse.setOrderProductId("111222");
//        orderResponse.setStatus("failed");
//        orderResponse.setDescription("error in acquiring ip");
//
//        OrderResponseHandler orderResponseHandler = new OrderResponseHandler();
//        orderResponseHandler.postMarketplaceServiceOrderResponse(orderResponse);
//
//    }
//
//
//    @Override
//    protected RouteBuilder createRouteBuilder() throws Exception {
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                OrderResponseHandler orderResponseHandler = new OrderResponseHandler();
//
//                //orderResponseEndPoint
//                from("direct:orderResponseEndPoint")
//                        .to("mock:result");
//
//            }
//        };
//    }
}
