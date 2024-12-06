package com.travelopedia.fun.itinerary_service.activity.services;

import java.util.List;

import com.travelopedia.fun.itinerary_service.activity.dto.ActivityDTO;
import com.travelopedia.fun.itinerary_service.activity.entity.Activity;

public interface ActivityService {

	List<ActivityDTO> getAllActivities();

	List<ActivityDTO> getAllActivitiesByItineraryId(Long ItineraryId);

	ActivityDTO getActivityById(Long activityId);

	ActivityDTO createActivity(Long tripId, Long itineraryId, Activity activity);

	ActivityDTO updateActivity(Long tripId,Long itineraryId, Long activityId, Activity activity);

	String deleteActivity(Long tripId,Long itineraryId, Long activityId);

}
