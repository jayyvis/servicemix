package com.bah.cloudengine.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on:
 * Date: 7/7/14
 * Time: 9:11 PM
 */
public class ServiceTemplateResource {
    //servicemix response url, username, password, port,
    private String href;
    private String servicemix_url;
    private String order_id;
    private String order_product_id;
    private String mpuuid;
    private String chef_url;
    private String chef_client_name;
    private String chef_signing_key_filename;
    private String aws_access_key_id;
    private String aws_secret_access_key;
    private String termination_date;
    private String approver;
    private String nte_budget;
    private String cc;
    private List<Map> tags = new ArrayList<Map>();
    private String requester_full_name;
    private List<String> chef_roles;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getServicemix_url() {
        return servicemix_url;
    }

    public void setServicemix_url(String servicemix_url) {
        this.servicemix_url = servicemix_url;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(String order_product_id) {
        this.order_product_id = order_product_id;
    }

    public String getMpuuid() {
        return mpuuid;
    }

    public void setMpuuid(String mpuuid) {
        this.mpuuid = mpuuid;
    }

    public String getChef_url() {
        return chef_url;
    }

    public void setChef_url(String chef_url) {
        this.chef_url = chef_url;
    }

    public String getChef_client_name() {
        return chef_client_name;
    }

    public void setChef_client_name(String chef_client_name) {
        this.chef_client_name = chef_client_name;
    }

    public String getChef_signing_key_filename() {
        return chef_signing_key_filename;
    }

    public void setChef_signing_key_filename(String chef_signing_key_filename) {
        this.chef_signing_key_filename = chef_signing_key_filename;
    }

    public String getAws_access_key_id() {
        return aws_access_key_id;
    }

    public void setAws_access_key_id(String aws_access_key_id) {
        this.aws_access_key_id = aws_access_key_id;
    }

    public String getAws_secret_access_key() {
        return aws_secret_access_key;
    }

    public void setAws_secret_access_key(String aws_secret_access_key) {
        this.aws_secret_access_key = aws_secret_access_key;
    }

    public String getTermination_date() {
        return termination_date;
    }

    public void setTermination_date(String termination_date) {
        this.termination_date = termination_date;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getNte_budget() {
        return nte_budget;
    }

    public void setNte_budget(String nte_budget) {
        this.nte_budget = nte_budget;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public List<Map> getTags() {
        return tags;
    }

    public void setTags(List<Map> tags) {
        this.tags = tags;
    }

    public String getRequester_full_name() {
        return requester_full_name;
    }

    public void setRequester_full_name(String requester_full_name) {
        this.requester_full_name = requester_full_name;
    }

    public List<String> getChef_roles() {
        return chef_roles;
    }

    public void setChef_roles(List<String> chef_roles) {
        this.chef_roles = chef_roles;
    }
}
