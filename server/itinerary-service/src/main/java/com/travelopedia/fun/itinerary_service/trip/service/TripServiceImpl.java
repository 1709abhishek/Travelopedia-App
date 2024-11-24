package com.travelopedia.fun.itinerary_service.trip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelopedia.fun.itinerary_service.exception.EntityAlreadyExistsException;
import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.trip.beans.Trip;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.mapper.TripMapper;
import com.travelopedia.fun.itinerary_service.trip.repository.TripRepository;
import com.travelopedia.fun.itinerary_service.utils.Utils;

/**
 * Service implementation for managing trips in the Travelopedia application.
 * Provides methods for creating, reading, updating, and deleting trips.
 */
@Service
public class TripServiceImpl implements TripService {

	private TripRepository tripRepository;
	private TripMapper tripMapper;

	/**
	 * Constructor to initialize dependencies for the TripServiceImpl.
	 * 
	 * @param tripRepository The repository for accessing trip data.
	 * @param tripMapper     The mapper for converting between Trip and TripDTO.
	 */
	public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper) {
		this.tripRepository = tripRepository;
		this.tripMapper = tripMapper;
	}

	/**
	 * Retrieves all trips in the system.
	 * 
	 * @return A list of TripDTO objects representing all trips.
	 */
	@Override
	public List<TripDTO> getAllTrips() {
		return tripRepository.findAll().stream().map(tripMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Retrieves a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be retrieved.
	 * @return The Trip object corresponding to the trip.
	 * @throws ResourceNotFoundException If no trip is found with the provided ID.
	 */
	@Override
	public Trip getTripById(Long tripId) {
		return tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
	}

	/**
	 * Creates a new trip.
	 * 
	 * @param trip The Trip object containing the details to be saved.
	 * @return The created TripDTO object.
	 * @throws EntityAlreadyExistsException If a trip with the same country and city
	 *                                      already exists.
	 */
	@Transactional
	@Override
	public TripDTO createTrip(Trip trip) {
		Utils.validateTrip(trip);
		if (tripRepository.existsByCountryAndCity(trip.getCountry(), trip.getCity())) {
			throw new EntityAlreadyExistsException(
					"Trip with the country " + trip.getCountry() + " and city " + trip.getCity() + " already exists.");
		}
		Trip savedTrip = tripRepository.save(trip);
		return tripMapper.toDTO(savedTrip);
	}

	/**
	 * Updates an existing trip.
	 * 
	 * @param tripId The ID of the trip to be updated.
	 * @param trip   The Trip object containing the updated details.
	 * @return The updated TripDTO object.
	 * @throws ResourceNotFoundException If no trip is found with the provided ID.
	 */
	@Transactional
	@Override
	public TripDTO updateTrip(Long tripId, Trip trip) {
		Trip existingTrip = tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
		Utils.validateTrip(trip);
		tripMapper.updateExistingTrip(existingTrip, trip);
		Trip updatedTrip = tripRepository.save(existingTrip);
		return tripMapper.toDTO(updatedTrip);
	}

	/**
	 * Deletes a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be deleted.
	 * @return A message confirming the deletion of the trip.
	 * @throws ResourceNotFoundException If no trip is found with the provided ID.
	 */
	@Transactional
	@Override
	public String deleteTrip(Long tripId) {
		if (!tripRepository.existsById(tripId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		tripRepository.deleteById(tripId);
		return "Trip with id " + tripId + " deleted successfully.";
	}
}
