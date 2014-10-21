package com.bah.cloudengine.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created on:
 * Date: 7/7/14
 * Time: 9:45 PM
 */
@JsonIgnoreProperties("options")
public class ServiceOrderRequest {
    private String id;
    private String approval_state;
    private String description;
    private String request_state;
    private String request_type;
    private String created_on;
    private String updated_on;
    private String message;
    private String destination_id;
    private String destination_type;
    private String fulfilled_on;
    private Integer requester_id;
    private String requester_name;
    private Integer source_id;
    private String source_type;
    private String status;
    private String userid;
    private String type;

    public Integer getIdAsInteger() {
        String[] segments = getId().split("/");
        return Integer.valueOf(segments[segments.length - 1]);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Integer request_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }


    public String getApproval_state() {
        return approval_state;
    }

    public void setApproval_state(String approval_state) {
        this.approval_state = approval_state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequest_state() {
        return request_state;
    }

    public void setRequest_state(String request_state) {
        this.request_state = request_state;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String getDestination_type() {
        return destination_type;
    }

    public void setDestination_type(String destination_type) {
        this.destination_type = destination_type;
    }

    public String getFulfilled_on() {
        return fulfilled_on;
    }

    public void setFulfilled_on(String fulfilled_on) {
        this.fulfilled_on = fulfilled_on;
    }

    public Integer getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(Integer requester_id) {
        this.requester_id = requester_id;
    }

    public String getRequester_name() {
        return requester_name;
    }

    public void setRequester_name(String requester_name) {
        this.requester_name = requester_name;
    }

    public Integer getSource_id() {
        return source_id;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
