package com.travelopedia.fun.itinerary_service.itinerary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;

public interface ItineraryRepository extends MongoRepository<Itinerary, Integer>{
	
	public Itinerary findByIternaryId(int itineraryId);

}
