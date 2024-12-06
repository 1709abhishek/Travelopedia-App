package com.travelopedia.fun.itinerary_service.itinerary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.travelopedia.fun.itinerary_service.activity.entity.Activity;
import com.travelopedia.fun.itinerary_service.activity.repository.ActivityRepository;
import com.travelopedia.fun.itinerary_service.activity.services.ActivityService;
import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.mapper.ItineraryMapper;
import com.travelopedia.fun.itinerary_service.itinerary.repository.ItineraryRepository;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;
import com.travelopedia.fun.itinerary_service.trip.repository.TripRepository;
import com.travelopedia.fun.itinerary_service.trip.services.TripService;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private ItineraryRepository itineraryRepository;
	private ItineraryMapper itineraryMapper;
	private TripService tripService;
	private TripRepository tripRepository;
	private ActivityRepository activityRepository;
	private ActivityService activityService;

	public ItineraryServiceImpl(ItineraryRepository itineraryRepository, ItineraryMapper itineraryMapper,
			TripService tripService, TripRepository tripRepository, ActivityRepository activityRepository,
			ActivityService activityService) {
		super();
		this.itineraryRepository = itineraryRepository;
		this.itineraryMapper = itineraryMapper;
		this.tripService = tripService;
		this.tripRepository = tripRepository;
		this.activityRepository = activityRepository;
		this.activityService = activityService;
	}

	@Override
	public List<ItineraryDTO> getAllItineraries() {
		return itineraryRepository.findAll().stream().map(itineraryMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Itinerary getItineraryByTripAndItineraryId(Long tripId, Long itineraryId) {
		Optional<Itinerary> itinerary = itineraryRepository.findByTripIdAndItineraryId(tripId, itineraryId);
		return itinerary.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found for tripId: " + tripId));
	}

	@Override
	public ItineraryDTO createItinerary(Long tripId, Itinerary itinerary) {
		if (!tripService.checkTripsExistence(tripId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		Trip trip = tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
		itinerary.setTrip(trip);
		Itinerary savedItinerary = itineraryRepository.save(itinerary);
		if (itinerary.getActivities() != null) {
			for (Activity activity : itinerary.getActivities()) {
				activity.setItinerary(savedItinerary);
				activityRepository.save(activity);
			}
		}
		return itineraryMapper.toDTO(savedItinerary);
	}

	@Override
	public ItineraryDTO updateItineray(Long tripId, Long itineraryId, Itinerary itinerary) {
		if (!tripService.checkTripsExistence(tripId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		Itinerary existingItinerary = itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));
		List<Activity> newActivities = itinerary.getActivities();
		for (Activity activity : newActivities) {
			activityService.createActivity(tripId, itineraryId, activity);
		}
		itineraryMapper.updateExistingItinerary(existingItinerary, itinerary);
		itineraryRepository.save(existingItinerary);
		return itineraryMapper.toDTO(existingItinerary);
	}

	@Override
	public String deleteItinerary(Long tripId, Long itineraryId) {
		Itinerary itinerary = itineraryRepository.findById(itineraryId)
				.orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));
		if (!itinerary.getTrip().getTripId().equals(tripId)) {
			throw new ResourceNotFoundException(
					"Itinerary with id " + itineraryId + " does not belong to Trip with id: " + tripId);
		}
		List<Activity> activities = itinerary.getActivities();
		for (Activity activity : activities) {
			activityRepository.deleteById(activity.getActivityId());
		}
		itineraryRepository.deleteById(itineraryId);
		return "Itinerary with id " + itineraryId + " deleted successfully.";
	}

	@Override
	public List<ItineraryDTO> getAllItinerariesByTripId(Long tripId) {
		List<Itinerary> itineraries = itineraryRepository.findByTrip_TripId(tripId);
		if (itineraries.isEmpty()) {
			throw new ResourceNotFoundException("No itineraries found for Trip with id: " + tripId);
		}
		return itineraries.stream().map(itineraryMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public boolean checkItineraryExistence(Long itineraryId) {
		return itineraryRepository.existsById(itineraryId);
	}

}
