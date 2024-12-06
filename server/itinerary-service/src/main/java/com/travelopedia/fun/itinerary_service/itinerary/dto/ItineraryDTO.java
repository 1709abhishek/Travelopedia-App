package com.travelopedia.fun.itinerary_service.itinerary.dto;

import java.util.List;

import com.travelopedia.fun.itinerary_service.activity.dto.ActivityDTO;

public class ItineraryDTO {

    private Long itineraryId;           
    private List<ActivityDTO> activities; 
    private Long tripId;                

    // Getters and setters
    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
