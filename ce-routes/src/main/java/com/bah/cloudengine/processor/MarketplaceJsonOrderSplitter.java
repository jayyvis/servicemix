package com.bah.cloudengine.processor;

import org.springframework.stereotype.Component;

/**
 * Created on:
 * Date: 1/27/14
 * Time: 10:03 AM
 */
@Component("marketplaceJsonOrderSplitter")
public class MarketplaceJsonOrderSplitter {
//
//    private final Logger logger = Logger.getLogger(this.getClass());
//    static final ObjectMapper mapper = new ObjectMapper();
//    static ProducerTemplate producerTemplate;
//
//    public List<ServiceOrder> jsonOrderSplitter(String marketplaceJsonOrder) throws IOException, InputValidationException {
//        logger.info("Splitting Json Order coming from UberCart");
//
//        List<ServiceOrder> singleOrdersList = new ArrayList<ServiceOrder>();
//
//        JsonNode root = mapper.readTree(marketplaceJsonOrder);
//
//        logger.debug("Json from Marketplace:");
//        logger.debug(marketplaceJsonOrder);
//        logger.debug("Processing order id: " + root.get("order_id").asText());
//
//        for (JsonNode product : root.findValue("products")) {
//            ServiceOrder singleOrder = new ServiceOrder();
//
//            //Get Parent Order information from root of json tree
//            singleOrder.setOrderId(Integer.valueOf(InputValidationUtil.validateInputInt(root.get("order_id").asText().trim(), true)));
//            singleOrder.setCustomerFirstName(InputValidationUtil.validateInputStr(root.get("first_name").asText().trim(), true));
//            singleOrder.setCustomerLastName(InputValidationUtil.validateInputStr(root.get("last_name").asText().trim(), true));
//            singleOrder.setOrganizationName(InputValidationUtil.validateInputStr(root.get("organization").asText().trim(), true));
//            singleOrder.setCustomerEmail(InputValidationUtil.validateInputEmail(root.get("email").asText().trim(), true));
//            singleOrder.setLocationCode(InputValidationUtil.validateInputStr(root.get("location_code").asText().toLowerCase().trim(), true));
//            singleOrder.setApplicationCode(InputValidationUtil.validateInputStr(root.get("application_code").asText().toLowerCase().trim(), true));
//
//            singleOrder.setOrderProductId(Integer.valueOf(InputValidationUtil.validateInputInt(product.get("order_product_id").asText(), true)));
//            logger.debug("----> Splitting Order : " + String.valueOf(singleOrder.getOrderId())
//                    + "Product Order ID: " + String.valueOf(singleOrder.getOrderProductId()));
//
//            //get vm label
//            singleOrder.setVmLabel(InputValidationUtil.validateInputStr(product.get("vm_name").asText().trim(), true));
//            //get service quantity
//            singleOrder.setServiceQuantity(Integer.valueOf(InputValidationUtil.validateInputInt(product.get("quantity").asText().trim(), true)));
//            //get vm os
//            singleOrder.setOs(InputValidationUtil.validateInputStr(product.get("vm_os").asText().toLowerCase().trim(), true));
//
//            //service type is named type in json -- server, labor, etc
//            singleOrder.setServiceType(InputValidationUtil.validateInputStr(product.get("type").asText()
//                    .toLowerCase().trim(), true));
//
//            //get Size from instance field
//            String[] sizeSplit = product.get("vm_instance").asText()
//                    .toLowerCase().trim().split(":");
//
//
//            singleOrder.setVmRole(InputValidationUtil.validateInputStr(product.get("vm_apps").asText().toLowerCase().trim(), true));
//
//            singleOrder.setNetworkType(InputValidationUtil.validateInputStr(product.get("vm_net").asText().trim(), true));
//
//            singleOrder.setStorageSize(Integer.valueOf(InputValidationUtil.validateInputInt(product.get("vm_disk").asText(), true)));
//
//            singleOrder.setStorageType(InputValidationUtil.validateInputStr(product.get("vm_disk_ops").asText().toLowerCase().trim(), true));
//
//            if (product.get("eo_mon") != null && product.get("eo_mon").asText().toLowerCase().trim().contains("yes"))
//                singleOrder.setMonitoringService(Boolean.TRUE);
//            else
//                singleOrder.setMonitoringService(Boolean.FALSE);
//
//            if (product.get("elasticity").asText().toLowerCase().trim().contains("yes"))
//                singleOrder.setElasticityService(Boolean.TRUE);
//            else
//                singleOrder.setElasticityService(Boolean.FALSE);
//
//            singleOrdersList.add(singleOrder);
//            System.out.println(singleOrder.toString());
//        }
//
//        return singleOrdersList;
//
//    }


}
