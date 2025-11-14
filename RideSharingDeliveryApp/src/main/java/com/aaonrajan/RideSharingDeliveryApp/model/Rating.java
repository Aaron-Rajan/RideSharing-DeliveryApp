package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "ratingId")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ_GEN")
    @SequenceGenerator(name = "RATING_SEQ_GEN", sequenceName = "RATING_SEQ")
    private long ratingId;
    private Long driverScore;
    private Long riderScore;
    private String comment;
    @OneToOne(mappedBy = "rating")
    @JsonIdentityReference(alwaysAsId = true)
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "FK_DRIVER_ID")
    @JsonIdentityReference(alwaysAsId = true)
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "FK_RIDER_ID")
    @JsonIdentityReference(alwaysAsId = true)
    private Rider rider;

    public Rating() {
        super();
    }

    public Rating(long ratingId, Long driverScore, Long riderScore, String comment, Trip trip, Driver driver, Rider rider) {
        super();
        this.ratingId = ratingId;
        this.driverScore = driverScore;
        this.riderScore = riderScore;
        this.comment = comment;
        this.trip = trip;
        this.driver = driver;
        this.rider = rider;
    }

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getDriverScore() {
        return driverScore;
    }

    public void setDriverScore(Long driverScore) {
        this.driverScore = driverScore;
    }

    public Long getRiderScore() {
        return riderScore;
    }

    public void setRiderScore(Long riderScore) {
        this.riderScore = riderScore;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
