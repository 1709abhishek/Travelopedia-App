package com.travelopedia.fun.itinerary_service.itinerary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelopedia.fun.itinerary_service.trip.Trip;
import com.travelopedia.fun.itinerary_service.trip.TripService;

@Service
public class ItineraryService {
	
	private final ItineraryRepository itineraryRepository;
	private final TripService tripService;

	public ItineraryService(ItineraryRepository itineraryRepository,TripService tripService) {
		super();
		this.itineraryRepository = itineraryRepository;
		this.tripService = tripService;
	}
	
	public List<Itinerary> findAllItineraries(){
		return itineraryRepository.findAll();
	}
	
	public Optional<Itinerary> findItineraryById(Long itineraryId){
		return itineraryRepository.findById(itineraryId);
	}
	
	public List<Itinerary> fetchAllItinerariesByTripId(Long tripId){
		return itineraryRepository.findAllByTripId(tripId);
	}
	
	@Transactional
	public Itinerary createOrUpdateItinerary(Itinerary itinerary, Long tripId) {
		boolean tripExists = tripService.findTripById(tripId).isPresent();

	    if (!tripExists) {
	        throw new RuntimeException("Trip does not exist");
	    }
	    Trip trip = tripService.findTripById(tripId).get();
	    itinerary.setTrip(trip);
		return itineraryRepository.save(itinerary);
	}
	
	@Transactional
	public void deletItinerary(Long itineraryId) {
		itineraryRepository.deleteById(itineraryId);
	}

}
