package com.travelopedia.fun.itinerary_service.activity.mapper;

import com.travelopedia.fun.itinerary_service.activity.dto.ActivityDTO;
import com.travelopedia.fun.itinerary_service.activity.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    // Convert Activity entity to ActivityDTO
    public ActivityDTO toDTO(Activity activity) {
        if (activity == null) {
            return null;
        }

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(activity.getActivityId());
        activityDTO.setTime(activity.getTime());
        activityDTO.setPlace(activity.getPlace());

        // Only the itineraryId is needed, not the entire itinerary object
        if (activity.getItinerary() != null) {
            activityDTO.setItineraryId(activity.getItinerary().getItineraryId());
        }

        return activityDTO;
    }

    // Update existing Activity entity with new Activity details
    public Activity updateExistingActivity(Activity existingActivity, Activity activity) {
        if (existingActivity == null || activity == null) {
            return null;
        }

        // Update fields of existing activity with the new data
        existingActivity.setTime(activity.getTime());
        existingActivity.setPlace(activity.getPlace());

        // If the new activity has a new itinerary, update the reference
        if (activity.getItinerary() != null) {
            existingActivity.setItinerary(activity.getItinerary());
        }

        return existingActivity;
    }
}
