package com.travelopedia.fun.itinerary_service.trip.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.travelopedia.fun.itinerary_service.exception.EntityAlreadyExistsException;
import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.dto.TripWithItinerariesDTO;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;
import com.travelopedia.fun.itinerary_service.trip.mapper.TripMapper;
import com.travelopedia.fun.itinerary_service.trip.repository.TripRepository;

@Service
public class TripServiceImpl implements TripService {

	private TripRepository tripRepository;
	private TripMapper tripMapper;

	public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper) {
		super();
		this.tripRepository = tripRepository;
		this.tripMapper = tripMapper;
	}

	@Override
	public List<TripDTO> getAllTrips() {
		return tripRepository.findAll().stream().map(tripMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public TripWithItinerariesDTO getTripWithItinerariesAndActivities(Long tripId) {
		Optional<Trip> trip = tripRepository.findById(tripId);
		return trip.map(tripMapper::tripWithItineariesToDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
	}

	@Override
	public TripDTO getTripById(Long tripId) {
		Optional<Trip> trip = tripRepository.findById(tripId);
		if(!trip.isPresent()) {
			throw new ResourceNotFoundException("Trip with id does not exist: " + tripId);
		}
		return trip.map(tripMapper::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
	}

	@Override
	public TripDTO createTrip(Trip trip) {
//		utils.validateTrip(trip);
		if (tripRepository.existsByCountryAndDestination(trip.getCountry(), trip.getDestination())) {
			throw new EntityAlreadyExistsException("Trip with the country " + trip.getCountry() + " and city "
					+ trip.getDestination() + " already exists.");
		}
		LocalDate currentDate = LocalDate.now();
		trip.setDate(currentDate);
		if (trip.getStartDate() != null && trip.getEndDate() != null) {
			long duration = ChronoUnit.DAYS.between(trip.getStartDate(), trip.getEndDate());
			trip.setDuration((int) duration);
		}
		Trip savedTrip = tripRepository.save(trip);
		return tripMapper.toDTO(savedTrip);
	}

	@Override
	public TripDTO updateTrip(Long tripId, Trip trip) {
//		utils.validateTrip(trip);
		Trip existingTrip = tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
		tripMapper.updateExistingTrip(existingTrip, trip);
		Trip updatedTrip = tripRepository.save(existingTrip);
		return tripMapper.toDTO(updatedTrip);
	}

	@Override
	public String deleteTrip(Long tripId) {
		if (!tripRepository.existsById(tripId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		tripRepository.deleteById(tripId);
		return "Trip with id " + tripId + " deleted successfully.";
	}

	@Override
	public boolean checkTripsExistence(Long tripId) {
		return tripRepository.existsById(tripId);
	}

}
