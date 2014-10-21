package com.bah.cloudengine.domain;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "description",
        "order_id",
        "order_product_id",
        "items"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MIQEngineServiceOrderResponse implements Serializable {

    private static final long serialVersionUID = -263497381290580232L;

    @JsonProperty("status")
    private String status;
    @JsonProperty("description")
    private String description;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("order_product_id")
    private String orderProductId;
    @JsonProperty("items")
    private List<MIQEngineServiceOrderResponseItems> items = new ArrayList<MIQEngineServiceOrderResponseItems>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("items")
    public List<MIQEngineServiceOrderResponseItems> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<MIQEngineServiceOrderResponseItems> items) {
        this.items = items;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
