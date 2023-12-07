package com.project.java.validationmicroservice.exceptions;

/**
 * Custom exception class to represent a runtime exception due to invalid parameters.
 */
public class InvalidParameterException extends RuntimeException {

    /**
     * Constructs a new InvalidParameterException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
