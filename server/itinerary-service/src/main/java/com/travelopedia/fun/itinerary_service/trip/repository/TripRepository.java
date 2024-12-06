package com.travelopedia.fun.itinerary_service.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelopedia.fun.itinerary_service.trip.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
	
	boolean existsByCountryAndDestination(String country, String destination);

}
