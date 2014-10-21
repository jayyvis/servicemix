package com.bah.cloudengine.processor;

import org.apache.camel.test.junit4.CamelTestSupport;

/**
 * Created on:
 * Date: 1/23/14
 * Time: 2:23 PM
 */
public class MarketplaceJsonOrderProcessorTest extends CamelTestSupport {
//
//    @EndpointInject(uri = "mock:results")
//    protected MockEndpoint resultEndpoint;
//
//    @Produce(uri = "direct:start")
//    protected ProducerTemplate template;
//
//    @Mock
//    private CloudEngineProperties properties;
//    @Mock
//    private HttpURLConnection httpConnection;
//    @Mock
//    private JbpmRestClient jbpmRestClient;
//
//    @InjectMocks
//    private MarketplaceJsonOrderProcessor marketplaceJsonOrderProcessor;
//
//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testProcess() throws Exception {
//        ServiceOrder singleOrder = new ServiceOrder();
//        singleOrder.setCustomerFirstName("JBPM");
//        singleOrder.setOrderId(112233);
//        singleOrder.setTemplateName("fedora_temp");
//        singleOrder.setCustomerEmail("park_john@bah.com");
//        singleOrder.setCustomerLastName("JBPMLast");
//        singleOrder.setVmName("jbpmtest_" + new Date().getTime());
//        singleOrder.setHostName("esx01");
//        singleOrder.setOs("linux");
//
//        when(properties.getJbpmRestURL()).thenReturn("http://www.google.com/");
//        when(properties.getActivemqBrokerURL()).thenReturn("vm://localhost?broker.persistent=false");
//        when(properties.getActivemqIncomingQueue()).thenReturn("TestQueue");
//        when(properties.getActivemqUser()).thenReturn("");
//        when(properties.getActivemqPassword()).thenReturn("");
//
//        when(httpConnection.getResponseCode()).thenReturn(200);
//
//        String inOrderProductId = "1234";
//
//        Mockito.doNothing().when(jbpmRestClient).startSingleOrderProcess(inOrderProductId);
//
//        template.sendBody(singleOrder);
//
//        resultEndpoint.expectedMessageCount(0);
//
//        resultEndpoint.assertIsSatisfied();
//    }
//
//    @Override
//    protected RouteBuilder createRouteBuilder() throws Exception {
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                marketplaceJsonOrderProcessor = new MarketplaceJsonOrderProcessor();
//
//                from("direct:start")
//                        .process(marketplaceJsonOrderProcessor)
//                        .to("mock:result");
//
//
//            }
//        };
//    }
}
