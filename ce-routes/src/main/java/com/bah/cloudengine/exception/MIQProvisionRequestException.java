package com.bah.cloudengine.exception;

public class MIQProvisionRequestException extends Exception {

    public MIQProvisionRequestException() {
    }

    public MIQProvisionRequestException(String message) {
        super(message);
    }

    public MIQProvisionRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
