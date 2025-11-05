package com.aaonrajan.RideSharingDeliveryApp.model;

public class TripDto {
    private String pickupAddress;
    private String dropoffAddress;
    private long duration;
    private String tripType;

    public TripDto() {
        super();
    }

    public TripDto(String pickupAddress, String dropoffAddress, long duration, String tripType) {
        super();
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.duration = duration;
        this.tripType = tripType;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
}
