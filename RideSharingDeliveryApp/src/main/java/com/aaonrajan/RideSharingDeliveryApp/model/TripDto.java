package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class TripDto {
    @Column
    @NotNull(message = "Pickup address is required")
    private String pickupAddress;
    @Column
    @NotNull(message = "Drop off address is required")
    private String dropoffAddress;
    @Column
    private long duration;
    @Column
    private long distance;
    @JsonProperty("isFragile")
    private boolean isFragile;
    @JsonProperty("isRemote")
    private boolean isRemote;
    @Column
    @NotNull(message = "Trip type is required")
    @Pattern(regexp = "ride|delivery", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Trip type must be 'ride' or 'delivery'")
    private String tripType;

    public TripDto() {
        super();
    }

    public TripDto(String pickupAddress, String dropoffAddress, long duration, long distance, boolean isFragile, boolean isRemote, String tripType) {
        super();
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.duration = duration;
        this.distance = distance;
        this.isFragile = isFragile;
        this.isRemote = isRemote;
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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
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
