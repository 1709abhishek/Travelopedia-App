package com.travelopedia.fun.itinerary_service.utils;

import com.travelopedia.fun.itinerary_service.exception.BadRequestException;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.trip.beans.Trip;

public class Utils {

	public static void validateTrip(Trip trip) {
		if (trip.getCity() == null || trip.getCity().isEmpty()) {
			throw new BadRequestException("City cannot be null or empty");
		}
		if (trip.getCountry() == null || trip.getCountry().isEmpty()) {
			throw new BadRequestException("Country cannot be null or empty");
		}
		if (trip.getStartDate() == null || trip.getEndDate() == null) {
			throw new BadRequestException("Start date and End date cannot be null");
		}
		if (trip.getEndDate() == null || trip.getEndDate() == null) {
			throw new BadRequestException("End date cannot be null");
		}
		if (trip.getQuote() == null || trip.getQuote().isEmpty()) {
			throw new BadRequestException("Country cannot be null or empty");
		}
	}

	public static void validateItinerary(Itinerary itinerary) {
		if (itinerary.getGoogleLink() == null || itinerary.getGoogleLink().isEmpty()) {
			throw new BadRequestException("Link for photos cannot be null or empty");
		}
		if (itinerary.getPlace() == null || itinerary.getPlace().isEmpty()) {
			throw new BadRequestException("Place cannot be null");
		}
		if (itinerary.getTime() == null) {
			throw new BadRequestException("Time cannot be null");
		}
	}

}
