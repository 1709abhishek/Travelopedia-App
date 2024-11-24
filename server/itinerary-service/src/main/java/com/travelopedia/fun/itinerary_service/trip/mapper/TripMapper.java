package com.travelopedia.fun.itinerary_service.trip.mapper;

import org.springframework.stereotype.Component;

import com.travelopedia.fun.itinerary_service.trip.beans.Trip;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
@Component
public class TripMapper {
	
	public TripDTO toDTO(Trip trip) {
		TripDTO tripDTO = new TripDTO();
		tripDTO.setTripId(trip.getTripId());
        tripDTO.setCity(trip.getCity());
        tripDTO.setCountry(trip.getCountry());
        tripDTO.setStartDate(trip.getStartDate());
        tripDTO.setEndDate(trip.getEndDate());
        tripDTO.setQuote(trip.getQuote());
        return tripDTO;
	}
	
	public Trip toEntity(TripDTO tripDTO) {
		Trip trip = new Trip();
//		trip.setTripId(tripDTO.getTripId());
        trip.setCity(tripDTO.getCity());
        trip.setCountry(tripDTO.getCountry());
        trip.setStartDate(tripDTO.getStartDate());
        trip.setEndDate(tripDTO.getEndDate());
        trip.setQuote(tripDTO.getQuote());
        return trip;	
     }
	
	public void updateExistingTrip(Trip existingTrip,Trip newTrip) {
		existingTrip.setCity(newTrip.getCity());
        existingTrip.setCountry(newTrip.getCountry());
        existingTrip.setStartDate(newTrip.getStartDate());
        existingTrip.setEndDate(newTrip.getEndDate());
        existingTrip.setQuote(newTrip.getQuote());
	}

}
