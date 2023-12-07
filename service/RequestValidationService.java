package com.project.java.validationmicroservice.service;

import com.project.java.validationmicroservice.exceptions.RequestParameterValidationException;
import com.project.java.validationmicroservice.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service class for validating query parameters in API requests.
 */
@Service
public class RequestValidationService {

    private ValidatorFactory validatorFactory;

    @Autowired
    public RequestValidationService(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    /**
     * Validates the presence and format of required query parameters.
     *
     * @param queryParams the map of query parameters
     * @throws RequestParameterValidationException if validation fails
     */
    public void validateQueryParameters(Map<String, String> queryParams) {
        // List of required query parameters
        List<String> requiredParams = Arrays.asList("sortType", "sortOrder", "limit", "offset");

        // Check if all required parameters are present
        for (String param : requiredParams) {
            if (!queryParams.containsKey(param)) {
                throw new RequestParameterValidationException("Parameter '" + param + "' is missing");
            }
        }

        // Retrieve values of required parameters
        String sortType = queryParams.get("sortType");
        String sortOrder = queryParams.get("sortOrder");
        String limit = queryParams.get("limit");
        String offset = queryParams.get("offset");

        // Check if any of the required parameters is empty
        if (sortOrder.isEmpty() || sortType.isEmpty() || limit.isEmpty() || offset.isEmpty()) {
            throw new RequestParameterValidationException("No query parameters can be empty");
        }

        // Validate sortType and sortOrder using alphabetical validator
        validatorFactory.getValidator("alphabetical").validate(sortType);
        validatorFactory.getValidator("alphabetical").validate(sortOrder);

        // Validate limit and offset using numeric validator
        validatorFactory.getValidator("numeric").validate(limit);
        validatorFactory.getValidator("numeric").validate(offset);

        // Convert limit and offset to integers for further validation
        int _limit = Integer.parseInt(limit);
        int _offset = Integer.parseInt(offset);

        // Validate limit within the range 1 to 5
        if (_limit < 1 || _limit > 5) {
            throw new RequestParameterValidationException("Limit should be in the range 1 to 5");
        }

        // Validate offset greater than or equal to 0
        if (_offset < 0) {
            throw new RequestParameterValidationException("Offset should be greater than or equal to 0");
        }
    }
}
