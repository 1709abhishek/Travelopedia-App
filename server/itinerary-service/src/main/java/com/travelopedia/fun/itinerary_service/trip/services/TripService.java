package com.travelopedia.fun.itinerary_service.trip.services;

import java.util.List;

import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.dto.TripWithItinerariesDTO;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;

/**
 * Service class for managing trip-related operations. Provides methods for
 * fetching, creating, updating, and deleting trips.
 */

public interface TripService {

	/**
	 * Retrieves all trips in the system.
	 * 
	 * @return A list of TripDTO objects representing all trips.
	 */
	List<TripDTO> getAllTrips();

	/**
	 * Retrieves all trips in the system.
	 * 
	 * @return A list of TripDTO objects representing all trips.
	 */
	TripWithItinerariesDTO getTripWithItinerariesAndActivities(Long tripId);

	/**
	 * Retrieves a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be retrieved.
	 * @return The Trip object corresponding to the trip.
	 */
	TripDTO getTripById(Long tripId);

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

	/**
	 * Deletes a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be deleted.
	 * @return A message confirming the deletion of the trip.
	 */
	String deleteTrip(Long tripId);

	boolean checkTripsExistence(Long tripId);

}
