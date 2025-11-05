package com.aaonrajan.RideSharingDeliveryApp.exceptions;

public class RiderAlreadyOnTripException extends RuntimeException {
    public RiderAlreadyOnTripException(String message) {
        super(message);
    }
}
