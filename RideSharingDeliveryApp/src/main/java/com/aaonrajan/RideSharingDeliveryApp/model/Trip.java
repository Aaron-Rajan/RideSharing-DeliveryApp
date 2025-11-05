package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRIP")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "tripId")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_SEQ_GEN")
    @SequenceGenerator(name = "TRIP_SEQ_GEN", sequenceName = "TRIP_SEQ")
    private long tripId;
    private String pickupAddress;
    private String dropoffAddress;
    private long duration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TripStatus status;
    @ManyToOne
    @JoinColumn(name = "FK_VEHICLE_ID")
    @JsonIdentityReference(alwaysAsId = true)
    private Vehicle vehicle;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "FK_RATING_NO")
    @JsonIdentityReference(alwaysAsId = true)
    private Rating rating;

    public Trip() {
        super();
    }

    public Trip(long tripId, String pickupAddress, String dropoffAddress, long duration, TripStatus status, Vehicle vehicle, Rating rating) {
        super();
        this.tripId = tripId;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.duration = duration;
        this.status = status;
        this.vehicle = vehicle;
        this.rating = rating;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
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

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", dropoffAddress='" + dropoffAddress + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                ", vehicle=" + vehicle +
                ", rating=" + rating +
                '}';
    }
}
