package com.travelopedia.fun.itinerary_service.itinerary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
	List<Itinerary> findByTrip_TripId(Long tripId);

	@Query("SELECT i FROM Itinerary i WHERE i.itineraryId = :itineraryId AND i.trip.tripId = :tripId")
	Optional<Itinerary> findByTripIdAndItineraryId(@Param("tripId") Long tripId,
			@Param("itineraryId") Long itineraryId);

}
