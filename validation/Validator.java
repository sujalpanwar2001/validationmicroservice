package com.project.java.validationmicroservice.validation;

/**
 * Interface for implementing validators to validate input strings.
 */
public interface Validator {

    /**
     * Validates the input string based on specific criteria.
     *
     * @param input the input string to be validated
     */
    void validate(String input);
}
