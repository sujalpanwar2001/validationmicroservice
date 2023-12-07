package com.project.java.validationmicroservice.exceptions;

/**
 * Custom exception class to represent a runtime exception due to API timeout.
 */
public class CustomApiTimeoutException extends RuntimeException {

    /**
     * Constructs a new CustomApiTimeoutException with the specified message.
     *
     * @param message the detail message
     */
    public CustomApiTimeoutException(String message) {
        super(message);
    }
}
