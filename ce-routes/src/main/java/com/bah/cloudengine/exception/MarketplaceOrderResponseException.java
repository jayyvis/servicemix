package com.bah.cloudengine.exception;

/**
 * Marketplace Order Response Exception
 */
public class MarketplaceOrderResponseException extends Exception{
    public MarketplaceOrderResponseException() {
    }

    public MarketplaceOrderResponseException(String message) {
        super(message);
    }

    public MarketplaceOrderResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
