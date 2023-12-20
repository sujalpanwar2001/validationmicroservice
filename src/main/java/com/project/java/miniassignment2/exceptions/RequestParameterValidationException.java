package com.project.java.miniassignment2.exceptions;

/**
 * Custom exception class to represent a runtime exception for request parameter validation.
 */
public class RequestParameterValidationException extends RuntimeException {

    /**
     * Constructs a new RequestParameterValidationException with the specified message.
     *
     * @param message the detail message
     */
    public RequestParameterValidationException(String message) {
        super(message);
    }
}
