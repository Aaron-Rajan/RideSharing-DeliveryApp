package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RIDE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "tripId")
public class Ride extends Trip {
    private long fare;
    @ManyToOne
    @JoinColumn(name = "FK_RIDER_ID")
    @JsonIdentityReference(alwaysAsId = true)
    private Rider rider;

    public Ride() {
        super();
    }

    public Ride(long tripId, String pickupAddress, String dropoffAddress, long duration, TripStatus status, Vehicle vehicle, Rating rating, long fare, Rider rider) {
        super(tripId, pickupAddress, dropoffAddress, duration, status, vehicle, rating);
        this.fare = fare;
        this.rider = rider;
    }

    public long getFare() {
        return fare;
    }

    public void setFare(long fare) {
        this.fare = fare;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
