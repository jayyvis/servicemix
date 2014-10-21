package com.bah.cloudengine.processor;

import org.apache.camel.test.junit4.CamelTestSupport;

/**
 * Marketplace Json Order test
 */
public class MarketplaceJsonOrderSplitterTest extends CamelTestSupport {
//
//    @EndpointInject(uri = "mock:result")
//    protected MockEndpoint resultEndpoint;
//
//    @Produce(uri = "direct:start")
//    protected ProducerTemplate template;
//
//    @Test
//    public void testJsonOrderSplitter() throws Exception {
//        MarketplaceJsonOrderSplitter marketplaceJsonOrderSplitter = new MarketplaceJsonOrderSplitter();
//
//        URL url = getClass().getResource("/testJSON.json");
//        File file = new File(url.getPath());
//
//        byte[] encoded = Files.readAllBytes(Paths.get(url.toURI()));
//
//        String jsonStr = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
//
//        List<ServiceOrder> singleOrderList = marketplaceJsonOrderSplitter.jsonOrderSplitter(jsonStr);
//
//        MockEndpoint mock = getMockEndpoint("mock:result");
//        mock.expectedMessageCount(3);
//
//        template.sendBody(jsonStr);
//
//        Thread.sleep(1000);
//
//        mock.assertIsSatisfied();
//
//    }
//
//    @Override
//    protected RouteBuilder createRouteBuilder() throws Exception {
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                MarketplaceJsonOrderSplitter marketplaceJsonOrderSplitter = new MarketplaceJsonOrderSplitter();
//
//                from("direct:start").onException(InputValidationException.class).handled(true)
//                        .transform(constant("Input validation error."))
//                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500)).end()
//                        .split().method(marketplaceJsonOrderSplitter, "jsonOrderSplitter")
//                        .to("mock:result");
//            }
//        };
//    }
}
