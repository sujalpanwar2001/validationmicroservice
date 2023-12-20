package com.project.java.miniassignment2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling specific exceptions across all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles RequestParameterValidationException and returns a custom error response.
     *
     * @param ex the exception to be handled
     * @return ResponseEntity containing the custom error response and HTTP status code
     */
    @ExceptionHandler(RequestParameterValidationException.class)
    public ResponseEntity<Object> handleRequestParameterValidationException(RequestParameterValidationException ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidParameterException and returns a custom error response.
     *
     * @param ex the exception to be handled
     * @return ResponseEntity containing the custom error response and HTTP status code
     */
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidSortingParametersException and returns a custom error response.
     *
     * @param ex the exception to be handled
     * @return ResponseEntity containing the custom error response and HTTP status code
     */
    @ExceptionHandler(InvalidSortingParametersException.class)
    public ResponseEntity<Object> handleInvalidSortingParametersException(InvalidSortingParametersException ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles CustomApiTimeoutException and returns a custom error response.
     *
     * @param ex the exception to be handled
     * @return ResponseEntity containing the custom error response and HTTP status code
     */
    @ExceptionHandler(CustomApiTimeoutException.class)
    public ResponseEntity<Object> handleCustomApiTimeoutException(CustomApiTimeoutException ex) {
        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.GATEWAY_TIMEOUT);
    }
}
