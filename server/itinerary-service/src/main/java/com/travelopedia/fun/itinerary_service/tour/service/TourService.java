package com.travelopedia.fun.itinerary_service.tour.service;

import java.util.List;
import java.util.Optional;

import com.travelopedia.fun.itinerary_service.dto.ResponseWrapper;
import com.travelopedia.fun.itinerary_service.tour.beans.Tour;

public interface TourService {
	
	public List<Tour> getAllTours();
	public Optional<Tour> fetchTour(int tourId);
	public ResponseWrapper<Tour> saveTourData(Tour tour);
	public ResponseWrapper<Tour> updateTourData(Tour tour);
	public ResponseWrapper<Tour> deleteTourData(int tourId);

}
