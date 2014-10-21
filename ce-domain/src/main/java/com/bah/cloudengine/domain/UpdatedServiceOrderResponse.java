package com.bah.cloudengine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Created by raymondvargas on 10/2/14.
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "status",
        "description",
        "order_id",
        "order_product_id",
        "order_product_type",
        "items"
})

public class UpdatedServiceOrderResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("order_product_id")
    private String orderProductId;
    @JsonProperty("order_product_type")
    private String orderProductType;
    @JsonProperty("items")
    private List<OrderResponseItem> items = new ArrayList<OrderResponseItem>();


    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("order_id")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("order_id")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("order_product_id")
    public String getOrderProductId() {
        return orderProductId;
    }

    @JsonProperty("order_product_id")
    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    @JsonProperty("order_product_type")
    public String getOrderProductType() {
        return orderProductType;
    }

    @JsonProperty("order_product_type")
    public void setOrderProductType(String orderProductType) {
        this.orderProductType = orderProductType;
    }

    @JsonProperty("items")
    public List<OrderResponseItem> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<OrderResponseItem> items) {
        this.items = items;
    }


}
