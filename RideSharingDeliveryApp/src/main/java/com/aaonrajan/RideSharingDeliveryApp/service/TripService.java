package com.aaonrajan.RideSharingDeliveryApp.service;

import com.aaonrajan.RideSharingDeliveryApp.exceptions.RiderAlreadyOnTripException;
import com.aaonrajan.RideSharingDeliveryApp.exceptions.TripNotFoundException;
import com.aaonrajan.RideSharingDeliveryApp.exceptions.UserNotFoundException;
import com.aaonrajan.RideSharingDeliveryApp.exceptions.VehicleNotFoundException;
import com.aaonrajan.RideSharingDeliveryApp.model.*;
import com.aaonrajan.RideSharingDeliveryApp.repository.RatingRepository;
import com.aaonrajan.RideSharingDeliveryApp.repository.TripRepository;
import com.aaonrajan.RideSharingDeliveryApp.repository.UserRepository;
import com.aaonrajan.RideSharingDeliveryApp.repository.VehicleRespoitory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepo;
    private final UserRepository userRepo;
    private final RatingRepository rateRepo;
    private final VehicleRespoitory vehicleRepo;
    private final RestTemplate restTemplate;
    private final IDistanceClient distanceClient;

    public TripService(TripRepository tripRepo, UserRepository userRepo, RatingRepository rateRepo, VehicleRespoitory vehicleRepo, RestTemplate restTemplate, IDistanceClient distanceClient) {
        super();
        this.tripRepo = tripRepo;
        this.userRepo = userRepo;
        this.rateRepo = rateRepo;
        this.vehicleRepo = vehicleRepo;
        this.restTemplate = restTemplate;
        this.distanceClient = distanceClient;
    }

    public List<Trip> getAllTrips() {
        return tripRepo.findAll();
    }

    public Trip getTrip(long id) {
        return tripRepo.findById(id).get();
    }

    public void deleteTrip(long id) {
        tripRepo.deleteById(id);
    }

    public List<Rating> getAllRatings() {
        return rateRepo.findAll();
    }

    public Trip requestTrip(long riderId, TripDto tripDto) {
        Trip trip = null;
        User user = userRepo.findById(riderId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Rider rider)) {
            throw new IllegalArgumentException("User is not a rider");
        }

        if (rider.isOnTrip()) {
            throw new RiderAlreadyOnTripException("Rider is already on an active trip");
        }

        if (tripDto.getTripType().equals("ride")) {
            Ride ride = new Ride();
            ride.setRider(rider);
            trip = ride;
        } else if (tripDto.getTripType().equals("delivery")) {
            Delivery delivery = new Delivery();
            trip = delivery;
        } //unnecessary
        BeanUtils.copyProperties(tripDto, trip, "tripId", "status", "vehicle", "rider");
        rider.setOnTrip(true);
        trip.setStatus(TripStatus.REQUESTED);
        return tripRepo.save(trip);
    }

    public Trip restClientRequestTrip(long riderId, TripDto tripDto) {
        Trip trip = null;
        User user = userRepo.findById(riderId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Rider rider)) {
            throw new IllegalArgumentException("User is not a rider");
        }

        if (rider.isOnTrip()) {
            throw new RiderAlreadyOnTripException("Rider is already on an active trip");
        }

        if (tripDto.getTripType().equals("ride")) {
            Ride ride = new Ride();
            ride.setRider(rider);
            trip = ride;
        } else if (tripDto.getTripType().equals("delivery")) {
            Delivery delivery = new Delivery();
            trip = delivery;
        } //unnecessary
        BeanUtils.copyProperties(tripDto, trip, "tripId", "status", "vehicle", "rider");
        String url = "http://localhost:8081/api/distances/getDistance?from={from}&to={to}";
        DistanceDto distanceDto = restTemplate.getForObject(
                url,
                DistanceDto.class,
                tripDto.getPickupAddress(),
                tripDto.getDropoffAddress()
        );
        trip.setDuration(distanceDto.getDurationSeconds());
        trip.setDistance(distanceDto.getDistanceMetres());
        rider.setOnTrip(true);
        trip.setStatus(TripStatus.REQUESTED);
        return tripRepo.save(trip);
    }

    public Trip feignClientRequestTrip(long riderId, TripDto tripDto) {
        Trip trip = null;
        User user = userRepo.findById(riderId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Rider rider)) {
            throw new IllegalArgumentException("User is not a rider");
        }

        if (rider.isOnTrip()) {
            throw new RiderAlreadyOnTripException("Rider is already on an active trip");
        }

        if (tripDto.getTripType().equals("ride")) {
            Ride ride = new Ride();
            ride.setRider(rider);
            trip = ride;
        } else if (tripDto.getTripType().equals("delivery")) {
            Delivery delivery = new Delivery();
            trip = delivery;
        } //unnecessary
        BeanUtils.copyProperties(tripDto, trip, "tripId", "status", "vehicle", "rider");
        DistanceDto distanceDto = distanceClient.getDistance(tripDto.getPickupAddress(), tripDto.getDropoffAddress());
        trip.setDuration(distanceDto.getDurationSeconds());
        trip.setDistance(distanceDto.getDistanceMetres());
        rider.setOnTrip(true);
        trip.setStatus(TripStatus.REQUESTED);
        return tripRepo.save(trip);
    }

    public Trip fiegnClientReqDelivery(TripDto tripDto) {
        Delivery delivery = new Delivery();
        BeanUtils.copyProperties(tripDto, delivery, "tripId", "status", "vehicle", "rider");
        DistanceDto distanceDto = distanceClient.getDistance(tripDto.getPickupAddress(), tripDto.getDropoffAddress());
        delivery.setDuration(distanceDto.getDurationSeconds());
        delivery.setDistance(distanceDto.getDistanceMetres());
        delivery.setStatus(TripStatus.REQUESTED);
        return tripRepo.save(delivery);

    }

    public void cancelTrip(long tripId, long riderId) {
        Trip trip = tripRepo.findById(tripId).
                orElseThrow(() -> new TripNotFoundException("Trip is not found"));
        if (!(trip instanceof Ride ride)) {
            throw new IllegalArgumentException("Trip is not a ride");
        }

        User user = userRepo.findById(riderId).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Rider rider)) {
            throw new IllegalArgumentException("User is not a rider");
        }

        if (ride.getRider() == null || ride.getRider().getUserId() != riderId) {
            throw new IllegalArgumentException("Rider does not own this ride" + ride.getRider().getUserId() + "*** " + riderId);
        }

        if (ride.getStatus() != TripStatus.REQUESTED) {
            throw new IllegalStateException("Trip cannot be cancelled by rider in its current status");
        }

        ride.setStatus(TripStatus.CANCELLED_BY_RIDER);
        rider.setOnTrip(false);
        tripRepo.save(ride);
    }

    public void deleteCancelledTrips() {
        List<Trip> trips = tripRepo.findAll();
        for (Trip trip: trips) {
            if (trip.getStatus().name().contains("CANCELLED")) {
                if ((trip instanceof Ride ride)) {
                    Rider rider = ride.getRider();
                    rider.getRides().remove(trip);
                    tripRepo.deleteById(trip.getTripId());
                }
            }
        }
    }
    public void completeTrip(long tripId) {
        Trip trip = tripRepo.findById(tripId).
                orElseThrow(() -> new TripNotFoundException("Trip is not found"));

        Vehicle vehicle = trip.getVehicle();
        if (vehicle == null) {
            throw new VehicleNotFoundException("There is no driver for the trip yet!");
        }

        User user = vehicle.getDriver();
        if (!(user instanceof Driver driver)) {
            throw new UserNotFoundException("User assigned to trip is not a driver");
        }

        if (trip.getVehicle() == null) {
           throw new RuntimeException("There is no driver for the trip yet!");
        }

        driver.setOnTrip(false);
        trip.setStatus(TripStatus.COMPLETED);
        tripRepo.save(trip);
        if (trip instanceof Ride ride) {
            Rider rider = ride.getRider();
            rider.setOnTrip(false);
            userRepo.save(rider);
        }
    }

    public Trip driverAcceptsTrip(long driverId, String plateNo, long rideId) {
        User user = userRepo.findById(driverId).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Driver driver)) {
            throw new IllegalArgumentException("User is not a driver");
        }

        Trip trip = tripRepo.findById(rideId).
                orElseThrow(() -> new TripNotFoundException("Trip is not found"));

        if (!(trip.getStatus().name().equals("REQUESTED"))) {
            throw new IllegalArgumentException("Trip status is: " + trip.getStatus().name());
        }

        if ((driver.isOnTrip())) {
            throw new IllegalArgumentException("Driver is already on a trip");
        }

        driver.setOnTrip(true);
        Vehicle vehicle = vehicleRepo.findByPlateNo(plateNo).
                orElseThrow(() -> new VehicleNotFoundException("Vehicle with plate number " + plateNo + " not found!"));
        vehicle.getTrips().add(trip);
        trip.setStatus(TripStatus.DRIVER_ASSIGNED);
        trip.setVehicle(vehicle);
        return tripRepo.save(trip);
    }

    public Rating rateDriver(long riderId, long rideId, Rating ratingDto) {
        User user = userRepo.findById(riderId).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user instanceof Rider rider)) {
            throw new IllegalArgumentException("User is not a rider");
        }

        Trip trip = tripRepo.findById(rideId).
                orElseThrow(() -> new TripNotFoundException("Trip is not found"));
        if (!(trip instanceof Ride ride)) {
            throw new IllegalArgumentException("Trip is not a ride");
        }

        if (!(rider.getRides().contains(ride))) {
            throw new IllegalArgumentException("This ride does not belong to this user");
        }

        if (!(ride.getStatus().name().equals("COMPLETED"))) {
            throw new IllegalArgumentException("The trip is not completed yet!");
        }

        User user2 = userRepo.findById(trip.getVehicle().getDriver().getUserId()).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!(user2 instanceof Driver driver)) {
            throw new IllegalArgumentException("User is not a driver");
        }

        Rating existingRating = rateRepo.findByTrip_TripId(rideId).orElse(null);

        if (existingRating != null) {
            if (existingRating.getDriverScore() != null) {
                throw new IllegalArgumentException("This trip has already been rated by this rider!");
            }

            existingRating.setDriverScore(ratingDto.getDriverScore());
            existingRating.setComment(ratingDto.getComment());
            return rateRepo.save(existingRating);
        }

        Rating rating = new Rating();
        BeanUtils.copyProperties(ratingDto, rating, "ratingId", "trip", "driver", "rider");
        rating.setTrip(trip);
        rating.setDriver(driver);
        rating.setRider(rider);
        trip.setRating(rating);
        driver.getRatings().add(rating);
        return rateRepo.save(rating);
    }

    public Rating rateRider(long driverId, long rideId, Rating ratingDto) {
        User user = userRepo.findById(driverId).
                orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!(user instanceof Driver driver)) {
            throw new IllegalArgumentException("User is not a driver");
        }

        Trip trip = tripRepo.findById(rideId).
                orElseThrow(() -> new TripNotFoundException("Trip is not found"));
        if (!(trip instanceof Ride ride)) {
            throw new IllegalArgumentException("Trip is not a ride");
        }

        if (!(driver.getVehicles().contains(trip.getVehicle()))) {
            throw new IllegalArgumentException("This driver does not own this ride");
        }

        if (!(ride.getStatus().name().equals("COMPLETED"))) {
            throw new IllegalArgumentException("The trip is not completed yet!");
        }

        Rider rider = ride.getRider();

        Rating existingRating = rateRepo.findByTrip_TripId(rideId).orElse(null);

        if (existingRating != null) {
            if (existingRating.getRiderScore() != null) {
                throw new IllegalArgumentException("This trip has already been rated by this driver!");
            }
            existingRating.setRiderScore(ratingDto.getRiderScore());
            return rateRepo.save(existingRating);
        }

        Rating newRating = new Rating();
        newRating.setTrip(trip);
        newRating.setDriver(driver);
        newRating.setRider(rider);
        newRating.setRiderScore(ratingDto.getRiderScore());
        trip.setRating(newRating);
        rider.getRatings().add(newRating);
        return rateRepo.save(newRating);
    }
}
