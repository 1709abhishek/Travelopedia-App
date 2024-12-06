package com.travelopedia.fun.itinerary_service.activity.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.travelopedia.fun.itinerary_service.activity.dto.ActivityDTO;
import com.travelopedia.fun.itinerary_service.activity.entity.Activity;
import com.travelopedia.fun.itinerary_service.activity.mapper.ActivityMapper;
import com.travelopedia.fun.itinerary_service.activity.repository.ActivityRepository;
import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.services.ItineraryService;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	private ItineraryService itineraryService;
	private ActivityMapper activityMapper;

	public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper,
			@Lazy ItineraryService itineraryService) {
		super();
		this.activityRepository = activityRepository;
		this.activityMapper = activityMapper;
		this.itineraryService = itineraryService;
	}

	@Override
	public List<ActivityDTO> getAllActivities() {
		return activityRepository.findAll().stream().map(activityMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<ActivityDTO> getAllActivitiesByItineraryId(Long itineraryId) {
		List<Activity> activities = activityRepository.findByItinerary_itineraryId(itineraryId);
		if (activities.isEmpty()) {
			throw new ResourceNotFoundException("No activities found with itinerary ID: " + itineraryId);
		}
		return activities.stream().map(activityMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public ActivityDTO getActivityById(Long activityId) {
		Optional<Activity> activity = activityRepository.findById(activityId);
		return activity.map(activityMapper::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
	}

	@Override
	public ActivityDTO createActivity(Long tripId, Long itineraryId, Activity activity) {
		if (!itineraryService.checkItineraryExistence(itineraryId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		Itinerary itinerary = itineraryService.getItineraryByTripAndItineraryId(tripId, itineraryId);
		activity.setItinerary(itinerary);
		Activity savedActivity = activityRepository.save(activity);
		return activityMapper.toDTO(savedActivity);
	}

	@Override
	public ActivityDTO updateActivity(Long tripId, Long itineraryId, Long activityId, Activity activity) {
		if (!itineraryService.checkItineraryExistence(itineraryId)) {
			throw new ResourceNotFoundException("Trip not found with id: " + tripId);
		}
		Activity existingActivity = activityRepository.findById(activityId)
				.orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
		if (!existingActivity.getItinerary().getItineraryId().equals(itineraryId)
				|| !existingActivity.getItinerary().getTrip().getTripId().equals(tripId)) {
			throw new ResourceNotFoundException("Activity with id " + activityId
					+ " does not belong to Itinerary with id: " + itineraryId + " in Trip with id: " + tripId);
		}
		existingActivity.setTime(activity.getTime());
		existingActivity.setPlace(activity.getPlace());
		Activity updatedActivity = activityRepository.save(existingActivity);
		return activityMapper.toDTO(updatedActivity);
	}

	@Override
	public String deleteActivity(Long tripId, Long itineraryId, Long activityId) {
		Activity activity = activityRepository.findByActivityIdByItineraryIdAndTripId(activityId, itineraryId, tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
		if (!activity.getItinerary().getItineraryId().equals(itineraryId)
				&& !activity.getItinerary().getTrip().getTripId().equals(tripId)) {
			throw new ResourceNotFoundException(
					"Activity with id: " + activityId + " does not belong to Itinerary with id: " + itineraryId);
		}
		activityRepository.deleteById(activityId);
		return "Activity with id " + activityId + " deleted successfully.";
	}

}
