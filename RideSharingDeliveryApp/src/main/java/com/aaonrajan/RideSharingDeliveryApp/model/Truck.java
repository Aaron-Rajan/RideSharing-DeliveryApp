package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TRUCK")
public class Truck extends Vehicle {
    private long cargoCapacity;

    public Truck() {
        super();
    }

    public Truck(long vehicleId, String make, String model, String colour, String plateNo, long seats, Driver driver, List<Trip> trips, long cargoCapacity) {
        super(vehicleId, make, model, colour, plateNo, seats, driver, trips);
        this.cargoCapacity = cargoCapacity;
    }

    public long getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(long cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
