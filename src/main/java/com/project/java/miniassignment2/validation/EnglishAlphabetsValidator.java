package com.project.java.miniassignment2.validation;

import com.project.java.miniassignment2.exceptions.RequestParameterValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Validator implementation for validating input strings containing only English alphabets.
 */
@Component
@Scope("singleton")
public class EnglishAlphabetsValidator implements Validator {

    /**
     * Validates whether the input string contains only English alphabets.
     *
     * @param input the input string to be validated
     * @throws RequestParameterValidationException if the input fails the alphabetical validation
     */
    @Override
    public void validate(String input) {
        if (!input.matches("[a-zA-Z]+")) {
            throw new RequestParameterValidationException("Alphabetical validation failed for input: " + input);
        }
    }
}
