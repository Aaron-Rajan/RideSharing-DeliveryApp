package com.aaonrajan.RideSharingDeliveryApp.repository;

import com.aaonrajan.RideSharingDeliveryApp.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
