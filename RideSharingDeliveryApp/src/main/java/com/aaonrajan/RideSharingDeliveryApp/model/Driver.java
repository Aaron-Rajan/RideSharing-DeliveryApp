package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="DRIVER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class Driver extends User {
    private double balance;
    private boolean isOnTrip;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Vehicle> vehicles;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Rating> ratings;

    public Driver() {
        super();
    }

    public Driver(long userId, String firstName, String lastName, String email, String phoneNo, boolean isActive, String password, Role role, double balance, boolean isOnTrip, List<Vehicle> vehicles, List<Rating> ratings) {
        super(userId, firstName, lastName, email, phoneNo, isActive, password, role);
        this.balance = balance;
        this.isOnTrip = isOnTrip;
        this.vehicles = vehicles;
        this.ratings = ratings;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public boolean isOnTrip() {
        return isOnTrip;
    }

    public void setOnTrip(boolean onTrip) {
        isOnTrip = onTrip;
    }
}
