package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class VehicleDto {
    @Column
    @NotNull(message = "Make is required")
    private String make;
    @Column
    @NotNull(message = "Model is required")
    private String model;
    @Column
    @NotNull(message = "Colour is required")
    private String colour;
    @NotBlank(message = "Plate number is required")
    @Size(min = 2, max = 15, message = "Plate number must be between 2 and 15 characters")
    @Pattern(
            regexp = "^[A-Z0-9-]+$",
            message = "Plate number may only contain capital letters, digits, and hyphens"
    )
    private String plateNo;
    @Column
    @NotNull(message = "Number of seats is required")
    @PositiveOrZero(message = "Number of seats must be zero or positive")
    private long seats;
    @JsonProperty("hasChildSeat")
    private boolean hasChildSeat;
    @JsonProperty("hasSnowTires")
    private boolean hasSnowTires;
    @JsonProperty("isElectric")
    private boolean isElectric;
    @Column
    @NotNull(message = "Cargo capacity is required")
    @PositiveOrZero(message = "Cargo capacity must be zero or positive")
    private long cargoCapacity;
    @Column
    @NotNull(message = "Vehicle type is required")
    @Pattern(regexp = "car|bike|truck", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Vehicle type must be 'car', 'bike', or 'truck'")
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
