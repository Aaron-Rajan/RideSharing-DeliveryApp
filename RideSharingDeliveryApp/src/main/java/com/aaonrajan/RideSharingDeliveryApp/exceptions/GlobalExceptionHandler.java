package com.aaonrajan.RideSharingDeliveryApp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 – not found: user, vehicle, trip

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex,
                                                       HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, "User not found", ex, request);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiError> handleVehicleNotFound(VehicleNotFoundException ex,
                                                          HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, "Vehicle not found", ex, request);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ApiError> handleTripNotFound(TripNotFoundException ex,
                                                       HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, "Trip not found", ex, request);
    }

    // 409 – conflict: rider already on trip, duplicate resources, etc.

    @ExceptionHandler(RiderAlreadyOnTripException.class)
    public ResponseEntity<ApiError> handleRiderAlreadyOnTrip(RiderAlreadyOnTripException ex,
                                                             HttpServletRequest request) {
        return buildError(HttpStatus.CONFLICT, "Rider already on trip", ex, request);
    }

    // Fallback – anything you didn’t explicitly handle above
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex,
                                                           HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex, request);
    }

    // Helper to build the response
    private ResponseEntity<ApiError> buildError(HttpStatus status,
                                                String errorTitle,
                                                Exception ex,
                                                HttpServletRequest request) {
        ApiError body = new ApiError(
                Instant.now(),
                status.value(),
                errorTitle,
                ex.getMessage(),          // your specific message from the thrown exception
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(body);
    }
}

