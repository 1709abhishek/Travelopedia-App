package com.travelopedia.fun.itinerary_service.itinerary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
