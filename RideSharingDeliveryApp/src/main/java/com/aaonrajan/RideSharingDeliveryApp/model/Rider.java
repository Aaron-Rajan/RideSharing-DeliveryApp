package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="RIDER")
public class Rider extends User {
    private boolean isOnTrip;
    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Ride> rides;
    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Rating> ratings;

    public Rider() {
        super();
    }

    public Rider(long userId, String firstName, String lastName, String email, String phoneNo, boolean isActive, boolean isOnTrip, List<Ride> rides, List<Rating> ratings) {
        super(userId, firstName, lastName, email, phoneNo, isActive);
        this.isOnTrip = isOnTrip;
        this.rides = rides;
        this.ratings = ratings;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public boolean isOnTrip() {
        return isOnTrip;
    }

    public void setOnTrip(boolean onTrip) {
        isOnTrip = onTrip;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
