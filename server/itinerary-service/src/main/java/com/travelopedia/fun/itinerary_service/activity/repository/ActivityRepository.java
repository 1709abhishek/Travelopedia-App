package com.travelopedia.fun.itinerary_service.activity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelopedia.fun.itinerary_service.activity.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

	List<Activity> findByItinerary_itineraryId(Long itineraryId);

	@Query("SELECT a FROM Activity a WHERE a.activityId = :activityId AND a.itinerary.itineraryId = :itineraryId AND a.itinerary.trip.tripId = :tripId")
	Optional<Activity> findByActivityIdByItineraryIdAndTripId(@Param("activityId") Long activityId,
			@Param("itineraryId") Long itineraryId, @Param("tripId") Long tripId);

}
