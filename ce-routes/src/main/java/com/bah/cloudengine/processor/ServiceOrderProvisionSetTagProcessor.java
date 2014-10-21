package com.bah.cloudengine.processor;

import com.bah.cloudengine.common.CloudEngineProperties;
import com.bah.cloudengine.domain.PostVmTag;
import com.bah.cloudengine.domain.ServiceOrder;
import com.bah.cloudengine.domain.ServiceOrderInstance;
import com.bah.cloudengine.webservice.MIQ.MiqRestClient;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on:
 * Date: 8/15/14
 * Time: 4:34 PM
 */
@Component("ServiceOrderProvisionSetTagProcessor")
public class ServiceOrderProvisionSetTagProcessor implements Processor {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private CloudEngineProperties properties;

    @Override
    public void process(Exchange exchange) throws Exception {
        ServiceOrder serviceOrder = exchange.getIn().getBody(ServiceOrder.class);
        MiqRestClient miqRestClient = new MiqRestClient();
        for (ServiceOrderInstance product : serviceOrder.getInstances()) {
            List<Integer> serviceVmsList = miqRestClient.miqGetServiceVms(product.getServiceOrderRequest().getRequest_id());


            ArrayList<PostVmTag.resource> tags = new ArrayList<PostVmTag.resource>();
            //TODO: grab tags from order or create tags from order and add to ArrayList tags...

            if (serviceVmsList.isEmpty()) {

            }
            for (Integer vmId : serviceVmsList) {
                miqRestClient.miqSetVMTag(vmId, tags);
            }
        }

        exchange.getIn().setHeader("tagged", true);
        exchange.setOut(exchange.getIn());


    }
}
