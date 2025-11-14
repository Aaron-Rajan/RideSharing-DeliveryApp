package com.aaonrajan.RideSharingDeliveryApp.repository;

import com.aaonrajan.RideSharingDeliveryApp.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByTrip_TripId(Long tripId);
}
