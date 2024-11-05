package com.travelopedia.fun.itinerary_service.itinerary.service;

import java.util.List;
import java.util.Optional;

import com.travelopedia.fun.itinerary_service.dto.ResponseWrapper;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;

public interface ItineraryService {
	
	public List<Itinerary> getAllItineraries();
	public Optional<Itinerary> fetchItinerary(int itineraryId);
	public ResponseWrapper<Itinerary> saveItineraryData(Itinerary itinerary);
	public ResponseWrapper<Itinerary> updateItineraryData(Itinerary itinerary);
	public ResponseWrapper<Itinerary> deleteItineraryData(int itineraryId);
	
}
