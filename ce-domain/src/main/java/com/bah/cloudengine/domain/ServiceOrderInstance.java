package com.bah.cloudengine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "order_product_id",
        "mpuuid",
        "service_catalog_id",
        "service_template_id",
        "order_service_type",
        "vm_name",
        "status"
})
public class ServiceOrderInstance {

    @JsonProperty("order_product_id")
    private String orderProductId;
    @JsonProperty("mpuuid")
    private String mpuuid;
    @JsonProperty("service_catalog_id")
    private String serviceCatalogId;
    @JsonProperty("service_template_id")
    private String serviceTemplateId;
    @JsonProperty("order_service_type")
    private String orderServiceType;
    @JsonProperty("vm_name")
    private String vmName;
    @JsonProperty("chef_roles")
    private List<String> chef_roles = new ArrayList<String>();
    @JsonProperty("status")
    private String status;
    @JsonProperty("tags")
    private List<Map> tags = new ArrayList<Map>();
    @JsonIgnore
    private ServiceOrderRequest serviceOrderRequest;

    @JsonProperty("chef_roles")
    public List<String> getChef_roles() {
        return chef_roles;
    }

    @JsonProperty("chef_roles")
    public void setChef_roles(List<String> chef_roles) {
        this.chef_roles = chef_roles;
    }


    @JsonProperty("order_product_id")
    public String getOrderProductId() {
        return orderProductId;
    }

    @JsonProperty("order_product_id")
    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    @JsonProperty("mpuuid")
    public String getMpuuid() {
        return mpuuid;
    }

    @JsonProperty("mpuuid")
    public void setMpuuid(String mpuuid) {
        this.mpuuid = mpuuid;
    }

    @JsonProperty("service_catalog_id")
    public String getServiceCatalogId() {
        return serviceCatalogId;
    }

    @JsonProperty("service_catalog_id")
    public void setServiceCatalogId(String serviceCatalogId) {
        this.serviceCatalogId = serviceCatalogId;
    }

    @JsonIgnore
    public Integer getServiceCatalogIdAsInt() {
        return Integer.parseInt(serviceCatalogId);
    }

    @JsonProperty("service_template_id")
    public String getServiceTemplateId() {
        return serviceTemplateId;
    }

    @JsonProperty("service_template_id")
    public void setServiceTemplateId(String serviceTemplateId) {
        this.serviceTemplateId = serviceTemplateId;
    }

    @JsonProperty("order_service_type")
    public String getOrderServiceType() {
        return orderServiceType;
    }

    @JsonProperty("order_service_type")
    public void setOrderServiceType(String orderServiceType) {
        this.orderServiceType = orderServiceType;
    }

    @JsonProperty("vm_name")
    public String getVmName() {
        return vmName;
    }

    @JsonProperty("vm_name")
    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public List<Map> getTags() {
        return tags;
    }

    public void setTags(List<Map> tags) {
        this.tags = tags;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public ServiceOrderRequest getServiceOrderRequest() {
        return serviceOrderRequest;
    }

    @JsonIgnore
    public void setServiceOrderRequest(ServiceOrderRequest serviceOrderRequest) {
        this.serviceOrderRequest = serviceOrderRequest;
    }

}
