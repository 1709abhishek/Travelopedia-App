package com.travelopedia.fun.itinerary_service.itinerary.services;

import java.util.List;

import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;

public interface ItineraryService {

	List<ItineraryDTO> getAllItineraries();

	List<ItineraryDTO> getAllItinerariesByTripId(Long tripId);

	Itinerary getItineraryByTripAndItineraryId(Long tripId, Long itineraryId);

	ItineraryDTO createItinerary(Long tripId, Itinerary itinerary);

	ItineraryDTO updateItineray(Long tripId, Long itineraryId, Itinerary itinerary);

	String deleteItinerary(Long tripId, Long itineraryId);

	boolean checkItineraryExistence(Long itineraryId);

}
