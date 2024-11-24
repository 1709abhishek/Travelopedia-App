package com.travelopedia.fun.itinerary_service.itinerary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.mapper.ItineraryMapper;
import com.travelopedia.fun.itinerary_service.itinerary.repository.ItineraryRepository;
import com.travelopedia.fun.itinerary_service.trip.beans.Trip;
import com.travelopedia.fun.itinerary_service.trip.service.TripService;
import com.travelopedia.fun.itinerary_service.utils.Utils;

/**
 * Service implementation for managing itineraries in the Travelopedia
 * application. Provides methods for creating, reading, updating, and deleting
 * itineraries.
 */
@Service
public class ItineraryServiceImpl implements ItineraryService {

	private final ItineraryRepository itineraryRepository;
	private final TripService tripService;
	private final ItineraryMapper itineraryMapper;

	/**
	 * Constructor to initialize dependencies for the ItineraryServiceImpl.
	 * 
	 * @param itineraryRepository The repository for accessing itinerary data.
	 * @param tripService         The service for managing trips.
	 * @param itineraryMapper     The mapper for converting between Itinerary and
	 *                            ItineraryDTO.
	 */
	public ItineraryServiceImpl(ItineraryRepository itineraryRepository, TripService tripService,
			ItineraryMapper itineraryMapper) {
		this.itineraryRepository = itineraryRepository;
		this.tripService = tripService;
		this.itineraryMapper = itineraryMapper;
	}

	/**
	 * Retrieves all itineraries in the system.
	 * 
	 * @return A list of ItineraryDTO objects representing all itineraries.
	 */
	@Override
	public List<ItineraryDTO> getAllItineraries() {
		return itineraryRepository.findAll().stream().map(itineraryMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Retrieves an itinerary by its unique ID.
	 * 
	 * @param itineraryId The ID of the itinerary to be retrieved.
	 * @return The ItineraryDTO object corresponding to the itinerary.
	 * @throws ResourceNotFoundException If no itinerary is found with the provided
	 *                                   ID.
	 */
	@Override
	public ItineraryDTO getItineraryById(Long itineraryId) {
		Itinerary itinerary = itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));
		return itineraryMapper.toDTO(itinerary);
	}

	/**
	 * Creates a new itinerary for an existing trip.
	 * 
	 * @param tripId    The ID of the trip for which the itinerary is to be created.
	 * @param itinerary The Itinerary object containing the details to be saved.
	 * @return The created ItineraryDTO object.
	 * @throws ResourceNotFoundException If the trip with the specified ID does not
	 *                                   exist.
	 */
	@Transactional
	@Override
	public ItineraryDTO createItinerary(Long tripId, Itinerary itinerary) {
		Utils.validateItinerary(itinerary);
		Trip checkTripsExistence = tripService.getTripById(tripId);
		if (checkTripsExistence == null) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		itinerary.setTrip(checkTripsExistence);
		Itinerary savedItinerary = itineraryRepository.save(itinerary);
		return itineraryMapper.toDTO(savedItinerary);
	}

	/**
	 * Updates an existing itinerary.
	 * 
	 * @param itineraryId The ID of the itinerary to be updated.
	 * @param itinerary   The Itinerary object containing the updated details.
	 * @return The updated ItineraryDTO object.
	 * @throws ResourceNotFoundException If no itinerary is found with the provided
	 *                                   ID.
	 */
	@Transactional
	@Override
	public ItineraryDTO updateItinerary(Long itineraryId, Itinerary itinerary) {
		Utils.validateItinerary(itinerary);
		Itinerary existingItinerary = itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));
		itineraryMapper.updateExistingItinerary(existingItinerary, itinerary);
		Itinerary updatedItinerary = itineraryRepository.save(existingItinerary);
		return itineraryMapper.toDTO(updatedItinerary);
	}

	/**
	 * Deletes an itinerary by its unique ID.
	 * 
	 * @param itineraryId The ID of the itinerary to be deleted.
	 * @return A message confirming the deletion of the itinerary.
	 * @throws ResourceNotFoundException If no itinerary is found with the provided
	 *                                   ID.
	 */
	@Transactional
	@Override
	public String deleteItinerary(Long itineraryId) {
		if (!itineraryRepository.existsById(itineraryId)) {
			throw new ResourceNotFoundException("Itinerary not found with id: " + itineraryId);
		}
		itineraryRepository.deleteById(itineraryId);
		return "Itinerary with id " + itineraryId + " deleted successfully.";
	}

}
