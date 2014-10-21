package com.bah.cloudengine.domain;

import java.util.List;

/**
 * Created on:
 * Date: 7/7/14
 * Time: 9:33 PM
 */
public class ServiceOrderRequestResponse {
    private List<ServiceOrderRequest> results;

    public List<ServiceOrderRequest> getResults() {
        return results;
    }

    public void setResults(List<ServiceOrderRequest> results) {
        this.results = results;
    }
}
