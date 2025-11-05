package com.aaonrajan.RideSharingDeliveryApp.controller;

import com.aaonrajan.RideSharingDeliveryApp.model.Rating;
import com.aaonrajan.RideSharingDeliveryApp.model.Trip;
import com.aaonrajan.RideSharingDeliveryApp.model.TripDto;
import com.aaonrajan.RideSharingDeliveryApp.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatcher/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        super();
        this.tripService = tripService;
    }

    @Operation(summary = "Getting all trips")
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
    @Operation(summary = "Getting trip by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable long id) {
        return ResponseEntity.ok(tripService.getTrip(id));
    }

    @Operation(summary = "Deleting trip by ID")
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable long id) {
        tripService.deleteTrip(id);
    }

    @Operation(summary = "Requesting a trip, assigning rider to trip")
    @PostMapping("/reqTrip")
    public ResponseEntity<Trip> requestTrip(@RequestParam long riderId, @RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripService.requestTrip(riderId, tripDto));
    }

    @Operation(summary = "Rider cancelling a trip")
    @PutMapping("/cancelRide")
    public void cancelTrip(@RequestParam long tripId, @RequestParam long riderId) {
        tripService.cancelTrip(tripId, riderId);
    }

    @Operation(summary = "Deleting all cancelled rides")
    @DeleteMapping("/deleteCancelledTrips")
    public void deleteCancelledTrips() {
        tripService.deleteCancelledTrips();
    }

    @Operation(summary = "Rating driver")
    @PostMapping("/rateDriver")
    public ResponseEntity<Rating> rateDriver(@RequestParam long riderId, @RequestParam long rideId,
                                             @RequestBody Rating rating) {
        return ResponseEntity.ok(tripService.rateDriver(riderId, rideId, rating));
    }

    @Operation(summary = "Getting all ratings")
    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(tripService.getAllRatings());
    }

    @Operation(summary = "Completing trip")
    @PostMapping("/completeRide")
    public void completeRide(@RequestParam long tripId) {
        tripService.completeTrip(tripId);
    }

    @Operation(summary = "Driver accepts ride")
    @PostMapping("/driverAcceptsRide")
    public ResponseEntity<Trip> driverAcceptsRide(@RequestParam long driverId, @RequestParam String plateNo,
                                                  @RequestParam long rideId) {
        return ResponseEntity.ok(tripService.driverAcceptsTrip(driverId, plateNo, rideId));
    }
}
