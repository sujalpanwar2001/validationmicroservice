package com.project.java.validationmicroservice.exceptions;

/**
 * Custom exception class to represent a runtime exception due to invalid sorting parameters.
 */
public class InvalidSortingParametersException extends RuntimeException {

    /**
     * Constructs a new InvalidSortingParametersException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidSortingParametersException(String message) {
        super(message);
    }
}
