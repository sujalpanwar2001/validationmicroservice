package com.project.java.miniassignment2.validation;

import com.project.java.miniassignment2.exceptions.RequestParameterValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Validator implementation for validating numeric input strings (excluding decimal values).
 */
@Component
@Scope("singleton")
public class NumericValidator implements Validator {

    /**
     * Validates whether the input string represents a numeric value (excluding decimal values).
     *
     * @param input the input string to be validated
     * @throws RequestParameterValidationException if the input fails the numeric validation
     */
    @Override
    public void validate(String input) {
        // Check if the input contains a decimal point (exclude decimal values)
        if (input.contains(".")) {
            throw new RequestParameterValidationException("Numeric validation failed for input: " + input + " (Decimal values are not allowed)");
        }

        try {
            // Attempt to parse the input as an integer
            int intValue = Integer.parseInt(input);

            // Additional validation logic can be added as needed

        } catch (NumberFormatException e) {
            // Handle the NumberFormatException and throw your custom exception
            throw new RequestParameterValidationException("Numeric validation failed for input: " + input);
        }
    }
}
