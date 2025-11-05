package com.aaonrajan.RideSharingDeliveryApp.model;

public enum TripStatus {
    REQUESTED,        // rider has requested, no driver assigned yet
    DRIVER_ASSIGNED,  // driver accepted / was assigned
    DRIVER_ARRIVING,  // driver is driving to pickup
    IN_PROGRESS,      // rider picked up
    COMPLETED,        // finished normally
    CANCELLED_BY_RIDER,
    CANCELLED_BY_DRIVER,
    CANCELLED_BY_SYSTEM
}

