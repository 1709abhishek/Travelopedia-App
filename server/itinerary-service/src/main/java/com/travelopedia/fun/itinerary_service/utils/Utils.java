package com.travelopedia.fun.itinerary_service.utils;

import com.travelopedia.fun.itinerary_service.trip.entity.Trip;

public class Utils {
	
// TODO
	public String validateTrip(Trip trip) {
		if (trip.getCountry() == null || trip.getCountry().isEmpty()) {
	        return "Country is required";
	    }
	    
	    if (trip.getDestination() == null || trip.getDestination().isEmpty()) {
	        return "Destination is required";
	    }

	    if (trip.getStartDate() == null) {
	        return "Start date is required";
	    }

	    if (trip.getEndDate() == null) {
	        return "End date is required";
	    }

	    if (trip.getStartDate().isAfter(trip.getEndDate())) {
	        return "Start date cannot be after end date";
	    }

	    if (trip.getDuration() <= 0) {
	        return "Duration must be greater than 0";
	    }
	    
	    if(trip.getDescription() == null || trip.getDescription().isEmpty()) {
	    	return "Destination is required";
	    }
	    return null;
		
	}
	
	

}
