package com.travelopedia.fun.itinerary_service.trip.service;

import java.util.List;

import com.travelopedia.fun.itinerary_service.trip.beans.Trip;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;

/**
 * Service interface for managing trips in the Travelopedia application.
 * Provides methods for creating, reading, updating, and deleting trips.
 */
public interface TripService {

	/**
	 * Retrieves all trips in the system.
	 * 
	 * @return A list of TripDTO objects representing all trips.
	 */
	List<TripDTO> getAllTrips();

	/**
	 * Retrieves a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be retrieved.
	 * @return The Trip object corresponding to the trip.
	 */
	Trip getTripById(Long tripId);

	/**
	 * Deletes a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be deleted.
	 * @return A message confirming the deletion of the trip.
	 */
	String deleteTrip(Long tripId);

	/**
	 * Creates a new trip.
	 * 
	 * @param trip The Trip object containing the details to be saved.
	 * @return The created TripDTO object.
	 */
	TripDTO createTrip(Trip trip);

	/**
	 * Updates an existing trip.
	 * 
	 * @param tripId The ID of the trip to be updated.
	 * @param trip   The Trip object containing the updated details.
	 * @return The updated TripDTO object.
	 */
	TripDTO updateTrip(Long tripId, Trip trip);
}
