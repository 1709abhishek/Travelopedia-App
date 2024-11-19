package com.travelopedia.fun.itinerary_service.itinerary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
	
	
	@Query("SELECT i FROM Itinerary i WHERE i.trip.id = :tripId")
    List<Itinerary> findAllByTripId(@Param("tripId") Long tripId);
	
//	@Modifying
//    @Query("DELETE FROM Itinerary i WHERE i.trip.id = :tripId")
//    void deleteAllByTripId(@Param("tripId") Long tripId);

}
