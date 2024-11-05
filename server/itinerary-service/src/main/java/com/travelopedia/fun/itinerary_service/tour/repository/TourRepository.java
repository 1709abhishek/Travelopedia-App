package com.travelopedia.fun.itinerary_service.tour.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.travelopedia.fun.itinerary_service.tour.beans.Tour;

public interface TourRepository extends MongoRepository<Tour, Integer>{
	
	public Tour findTourById(int tourId);

}
