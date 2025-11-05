package com.aaonrajan.RideSharingDeliveryApp.model;

public class VehicleDto {
    private String make;
    private String model;
    private String colour;
    private String plateNo;
    private long seats;
    private boolean hasChildSeat;
    private boolean hasSnowTires;
    private boolean isElectric;
    private long cargoCapacity;
    private String vehicleType;

    public VehicleDto() {
        super();
    }

    public VehicleDto(String make, String model, String colour, String plateNo, long seats, boolean hasChildSeat, boolean hasSnowTires, boolean isElectric, long cargoCapacity, String vehicleType) {
        super();
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.plateNo = plateNo;
        this.seats = seats;
        this.hasChildSeat = hasChildSeat;
        this.hasSnowTires = hasSnowTires;
        this.isElectric = isElectric;
        this.cargoCapacity = cargoCapacity;
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
