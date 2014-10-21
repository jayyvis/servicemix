package com.bah.cloudengine.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "order_id",
        "requester_first_name",
        "requester_last_name",
        "requester_email",
        "requester_uuid",
        "team",
        "project",
        "termination_date",
        "approver_first_name",
        "approver_last_name",
        "approver_email_address",
        "approver_uuid",
        "charge_code",
        "nte_year_budget",
        "order_status",
        "instances"
})
public class ServiceOrder implements Serializable {

    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("requester_first_name")
    private String requesterFirstName;
    @JsonProperty("requester_last_name")
    private String requesterLastName;
    @JsonProperty("requester_email")
    private String requesterEmail;
    @JsonProperty("requester_uuid")
    private String requesterUuid;
    @JsonProperty("team")
    private String team;
    @JsonProperty("project")
    private String project;
    @JsonProperty("termination_date")
    private String terminationDate;
    @JsonProperty("approver_first_name")
    private String approverFirstName;
    @JsonProperty("approver_last_name")
    private String approverLastName;
    @JsonProperty("approver_email_address")
    private String approverEmailAddress;
    @JsonProperty("approver_uuid")
    private String approverUuid;
    @JsonProperty("charge_code")
    private String chargeCode;
    @JsonProperty("nte_year_budget")
    private String nteYearBudget;
    @JsonProperty("order_status")
    private String orderStatus;
    @JsonProperty("href")
    private String href;
    @JsonProperty("servicemix_url")
    private String servicemixUrl;
    @JsonProperty("chef_url")
    private String chefUrl;
    @JsonProperty("chef_client_name")
    private String chefClientName;
    @JsonProperty("chef_signing_key_filename")
    private String chefSigningKeyFilename;
    @JsonProperty("aws_access_key_id")
    private String awsAccessKeyId;
    @JsonProperty("aws_secret_access_key")
    private String awsSecretAccessKey;
    @JsonProperty("instances")
    private List<ServiceOrderInstance> instances = new ArrayList<ServiceOrderInstance>();

    @JsonProperty("order_id")
    public Long getOrderId() {
        return orderId;
    }

    @JsonProperty("order_id")
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("requester_first_name")
    public String getRequesterFirstName() {
        return requesterFirstName;
    }

    @JsonProperty("requester_first_name")
    public void setRequesterFirstName(String requesterFirstName) {
        this.requesterFirstName = requesterFirstName;
    }

    @JsonProperty("requester_last_name")
    public String getRequesterLastName() {
        return requesterLastName;
    }

    @JsonProperty("requester_last_name")
    public void setRequesterLastName(String requesterLastName) {
        this.requesterLastName = requesterLastName;
    }

    @JsonProperty("requester_email")
    public String getRequesterEmail() {
        return requesterEmail;
    }

    @JsonProperty("requester_email")
    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    @JsonProperty("requester_uuid")
    public String getRequesterUuid() {
        return requesterUuid;
    }

    @JsonProperty("requester_uuid")
    public void setRequesterUuid(String requesterUuid) {
        this.requesterUuid = requesterUuid;
    }

    @JsonProperty("team")
    public String getTeam() {
        return team;
    }

    @JsonProperty("team")
    public void setTeam(String team) {
        this.team = team;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("termination_date")
    public String getTerminationDate() {
        return terminationDate;
    }

    @JsonProperty("termination_date")
    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    @JsonProperty("approver_first_name")
    public String getApproverFirstName() {
        return approverFirstName;
    }

    @JsonProperty("approver_first_name")
    public void setApproverFirstName(String approverFirstName) {
        this.approverFirstName = approverFirstName;
    }

    @JsonProperty("approver_last_name")
    public String getApproverLastName() {
        return approverLastName;
    }

    @JsonProperty("approver_last_name")
    public void setApproverLastName(String approverLastName) {
        this.approverLastName = approverLastName;
    }

    @JsonProperty("approver_email_address")
    public String getApproverEmailAddress() {
        return approverEmailAddress;
    }

    @JsonProperty("approver_email_address")
    public void setApproverEmailAddress(String approverEmailAddress) {
        this.approverEmailAddress = approverEmailAddress;
    }

    @JsonProperty("approver_uuid")
    public String getApproverUuid() {
        return approverUuid;
    }

    @JsonProperty("approver_uuid")
    public void setApproverUuid(String approverUuid) {
        this.approverUuid = approverUuid;
    }

    @JsonProperty("charge_code")
    public String getChargeCode() {
        return chargeCode;
    }

    @JsonProperty("charge_code")
    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    @JsonProperty("nte_year_budget")
    public String getNteYearBudget() {
        return nteYearBudget;
    }

    @JsonProperty("nte_year_budget")
    public void setNteYearBudget(String nteYearBudget) {
        this.nteYearBudget = nteYearBudget;
    }

    @JsonProperty("order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("order_status")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("servicemix_url")
    public String getServicemixUrl() {
        return servicemixUrl;
    }

    @JsonProperty("servicemix_url")
    public void setServicemixUrl(String servicemixUrl) {
        this.servicemixUrl = servicemixUrl;
    }

    @JsonProperty("chef_url")
    public String getChefUrl() {
        return chefUrl;
    }

    @JsonProperty("chef_url")
    public void setChefUrl(String chefUrl) {
        this.chefUrl = chefUrl;
    }

    @JsonProperty("chef_client_name")
    public String getChefClientName() {
        return chefClientName;
    }

    @JsonProperty("chef_client_name")
    public void setChefClientName(String chefClientName) {
        this.chefClientName = chefClientName;
    }

    @JsonProperty("chef_signing_key_filename")
    public String getChefSigningKeyFilename() {
        return chefSigningKeyFilename;
    }

    @JsonProperty("chef_signing_key_filename")
    public void setChefSigningKeyFilename(String chefSigningKeyFilename) {
        this.chefSigningKeyFilename = chefSigningKeyFilename;
    }

    @JsonProperty("aws_access_key_id")
    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    @JsonProperty("aws_access_key_id")
    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    @JsonProperty("aws_secret_access_key")
    public String getAwsSecretAccessKey() {
        return awsSecretAccessKey;
    }

    @JsonProperty("aws_secret_access_key")
    public void setAwsSecretAccessKey(String awsSecretAccessKey) {
        this.awsSecretAccessKey = awsSecretAccessKey;
    }

    @JsonProperty("instances")
    public List<ServiceOrderInstance> getInstances() {
        return instances;
    }

    @JsonProperty("instances")
    public void setInstances(List<ServiceOrderInstance> instances) {
        this.instances = instances;
    }

}
