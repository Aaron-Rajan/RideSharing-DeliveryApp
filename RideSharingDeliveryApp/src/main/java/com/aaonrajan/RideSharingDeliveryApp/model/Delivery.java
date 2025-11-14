package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DELIVERY")
public class Delivery extends Trip {
    private boolean isFragile;
    private boolean isRemote;

    public Delivery() {
        super();
    }

    public Delivery(long tripId, String pickupAddress, String dropoffAddress, long duration, long distance, TripStatus status, Vehicle vehicle, Rating rating, boolean isFragile, boolean isRemote) {
        super(tripId, pickupAddress, dropoffAddress, duration, distance, status, vehicle, rating);
        this.isFragile = isFragile;
        this.isRemote = isRemote;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }
}
