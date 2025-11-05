package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "VEHICLE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "vehicleId")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_SEQ_GEN")
    @SequenceGenerator(name = "VEHICLE_SEQ_GEN", sequenceName = "VEHICLE_SEQ")
    private long vehicleId;
    private String make;
    private String model;
    private String colour;
    private String plateNo;
    private long seats;
    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    @JsonIdentityReference(alwaysAsId = true)
    private Driver driver;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Trip> trips;


    public Vehicle() {
        super();
    }

    public Vehicle(long vehicleId, String make, String model, String colour, String plateNo, long seats, Driver driver, List<Trip> trips) {
        super();
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.plateNo = plateNo;
        this.seats = seats;
        this.driver = driver;
        this.trips = trips;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
