package com.bah.cloudengine.processor;

import com.bah.cloudengine.domain.MIQEngineServiceOrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created on:
 * Date: 8/15/14
 * Time: 12:27 PM
 */
@Component("ServiceOrderResponseProcessor")
public class ServiceOrderResponseProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonOrderResponseStr = exchange.getIn().getBody(String.class);
        MIQEngineServiceOrderResponse MIQEngineServiceOrderResponse = new ObjectMapper().readValue(jsonOrderResponseStr, MIQEngineServiceOrderResponse.class);
        exchange.getIn().setBody(MIQEngineServiceOrderResponse);
        exchange.setOut(exchange.getIn());

    }
}
