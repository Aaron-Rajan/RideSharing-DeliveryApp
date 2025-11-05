package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "CAR")
public class Car extends Vehicle {
    private boolean hasChildSeat;
    private boolean hasSnowTires;

    public Car() {
        super();
    }

    public Car(long vehicleId, String make, String model, String colour, String plateNo, long seats, Driver driver, List<Trip> trips, boolean hasChildSeat, boolean hasSnowTires) {
        super(vehicleId, make, model, colour, plateNo, seats, driver, trips);
        this.hasChildSeat = hasChildSeat;
        this.hasSnowTires = hasSnowTires;
    }

    public boolean isHasChildSeat() {
        return hasChildSeat;
    }

    public void setHasChildSeat(boolean hasChildSeat) {
        this.hasChildSeat = hasChildSeat;
    }

    public boolean isHasSnowTires() {
        return hasSnowTires;
    }

    public void setHasSnowTires(boolean hasSnowTires) {
        this.hasSnowTires = hasSnowTires;
    }
}
