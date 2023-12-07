package com.project.java.validationmicroservice.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Factory class for obtaining different types of validators.
 */
@Component
public class ValidatorFactory {

    private final Validator numericValidator;
    private final Validator alphabetsValidator;

    @Autowired
    public ValidatorFactory(@Qualifier("numericValidator") Validator numericValidator,
                            @Qualifier("englishAlphabetsValidator") Validator alphabetsValidator) {
        this.numericValidator = numericValidator;
        this.alphabetsValidator = alphabetsValidator;
    }

    /**
     * Retrieves a validator based on the provided type.
     *
     * @param type the type of validator to retrieve
     * @return the validator instance based on the type
     * @throws IllegalArgumentException if an invalid validator type is provided
     */
    public Validator getValidator(String type) {
        switch (type.toLowerCase()) {
            case "numeric":
                return numericValidator;
            case "alphabetical":
                return alphabetsValidator;
            default:
                throw new IllegalArgumentException("Invalid validator type: " + type);
        }
    }
}
