package com.travelopedia.fun.itinerary_service.itinerary.service;

import java.util.List;

import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;

/**
 * Service interface for managing itineraries. Provides methods to handle CRUD
 * operations and business logic related to itineraries.
 */
public interface ItineraryService {

	/**
	 * Retrieves all itineraries.
	 * 
	 * @return A list of ItineraryDTO objects representing all itineraries.
	 */
	List<ItineraryDTO> getAllItineraries();

	/**
	 * Retrieves a specific itinerary by its ID.
	 * 
	 * @param itineraryId The ID of the itinerary to retrieve.
	 * @return The ItineraryDTO representing the itinerary with the given ID.
	 * 
	 */
	ItineraryDTO getItineraryById(Long itineraryId);

	/**
	 * Creates a new itinerary for a specified trip.
	 * 
	 * @param tripId    The ID of the trip for which the itinerary is created.
	 * @param itinerary The Itinerary object containing the details of the new
	 *                  itinerary.
	 * @return The created ItineraryDTO object.
	 */
	ItineraryDTO createItinerary(Long tripId, Itinerary itinerary);

	/**
	 * Updates an existing itinerary with the provided details.
	 * 
	 * @param itineraryId The ID of the itinerary to update.
	 * @param itinerary   The Itinerary object containing the updated itinerary
	 *                    details.
	 * @return The updated ItineraryDTO object.
	 */
	ItineraryDTO updateItinerary(Long itineraryId, Itinerary itinerary);

	/**
	 * Deletes an itinerary by its ID.
	 * 
	 * @param itineraryId The ID of the itinerary to delete.
	 * @return A success message indicating the deletion status.
	 */
	String deleteItinerary(Long itineraryId);

}
