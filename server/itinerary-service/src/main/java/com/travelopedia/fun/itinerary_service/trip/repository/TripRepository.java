package com.travelopedia.fun.itinerary_service.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelopedia.fun.itinerary_service.trip.beans.Trip;

/**
 * Repository interface for performing CRUD operations on the Trip entity.
 * Extends JpaRepository to leverage Spring Data JPA functionality.
 */
public interface TripRepository extends JpaRepository<Trip, Long> {

	/**
	 * Checks if a trip with the specified country and city already exists in the
	 * system.
	 * 
	 * @param country The country associated with the trip.
	 * @param city    The city associated with the trip.
	 * @return {@code true} if a trip exists with the given country and city,
	 *         otherwise {@code false}.
	 */
	boolean existsByCountryAndCity(String country, String city);

}
