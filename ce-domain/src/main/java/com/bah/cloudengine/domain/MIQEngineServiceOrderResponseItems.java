package com.bah.cloudengine.domain;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Domain object representing the response items message back to client application.
 */
@JsonPropertyOrder({
        "public_fqdn",
        "hostname",
        "miq_guid",
        "ip_addr",
        "aws_instance_id",
        "tags"
})
public class MIQEngineServiceOrderResponseItems implements Serializable {

    @JsonProperty("public_fqdn")
    private String public_fqdn;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("miq_guid")
    private String miqGuid;
    @JsonProperty("ip_addr")
    private String ipAddr;
    @JsonProperty("aws_instance_id")
    private String awsInstanceId;
    @JsonProperty("mpuuid")
    private String mpuuid;


    @JsonProperty("mpuuid")
    public String getMpuuid() {
        return mpuuid;
    }

    @JsonProperty("mpuuid")
    public void setMpuuid(String mpuuid) {
        this.mpuuid = mpuuid;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("public_fqdn")
    public String getPublic_fqdn() {
        return public_fqdn;
    }

    @JsonProperty("public_fqdn")
    public void setPublic_fqdn(String public_fqdn) {
        this.public_fqdn = public_fqdn;
    }

    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @JsonProperty("miq_guid")
    public String getMiqGuid() {
        return miqGuid;
    }

    @JsonProperty("miq_guid")
    public void setMiqGuid(String miqGuid) {
        this.miqGuid = miqGuid;
    }

    @JsonProperty("ip_addr")
    public String getIpAddr() {
        return ipAddr;
    }

    @JsonProperty("ip_addr")
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @JsonProperty("aws_instance_id")
    public String getAwsInstanceId() {
        return awsInstanceId;
    }

    @JsonProperty("aws_instance_id")
    public void setAwsInstanceId(String awsInstanceId) {
        this.awsInstanceId = awsInstanceId;
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
