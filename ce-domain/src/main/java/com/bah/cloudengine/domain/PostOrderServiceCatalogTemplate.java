package com.bah.cloudengine.domain;

/**
 * Created on:
 * Date: 7/7/14
 * Time: 8:56 PM
 */
public class PostOrderServiceCatalogTemplate {
    private String action;
    private ServiceTemplateResource resource;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ServiceTemplateResource getResource() {
        return resource;
    }

    public void setResource(ServiceTemplateResource resource) {
        this.resource = resource;
    }
}
