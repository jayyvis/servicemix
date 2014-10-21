package com.bah.cloudengine.exception;

/**
 *
 */
public class JBPMExecutionException extends Exception {

    public JBPMExecutionException() {
    }

    public JBPMExecutionException(String message) {
        super(message);
    }

    public JBPMExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
