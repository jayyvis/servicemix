package com.bah.cloudengine.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by raymondvargas on 10/3/14.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "item_name",
        "fqdn",
        "hostname",
        "ip_addr"
})
public class OrderResponseItem {

    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("fqdn")
    private String fqdn;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("ip_addr")
    private String ipAddr;


    @JsonProperty("item_name")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("item_name")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("fqdn")
    public String getFqdn() {
        return fqdn;
    }

    @JsonProperty("fqdn")
    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @JsonProperty("ip_addr")
    public String getIpAddr() {
        return ipAddr;
    }

    @JsonProperty("ip_addr")
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
