package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "BIKE")
public class Bike extends Vehicle {
    private boolean isElectric;
    private long cargoCapacity;

    public Bike() {
        super();
    }

    public Bike(long vehicleId, String make, String model, String colour, String plateNo, long seats, Driver driver, List<Trip> trips, boolean isElectric, long cargoCapacity) {
        super(vehicleId, make, model, colour, plateNo, seats, driver, trips);
        this.isElectric = isElectric;
        this.cargoCapacity = cargoCapacity;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public long getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(long cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
