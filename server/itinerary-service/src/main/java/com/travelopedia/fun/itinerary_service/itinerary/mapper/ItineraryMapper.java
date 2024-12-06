package com.travelopedia.fun.itinerary_service.itinerary.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.travelopedia.fun.itinerary_service.activity.mapper.ActivityMapper;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;

@Component
public class ItineraryMapper {

	private final ActivityMapper activityMapper;
//    private final TripMapper tripMapper;

	public ItineraryMapper(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;

	}

	// Convert Itinerary entity to ItineraryDTO
	public ItineraryDTO toDTO(Itinerary itinerary) {
		if (itinerary == null) {
			return null;
		}

		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setItineraryId(itinerary.getItineraryId());
		if (itinerary.getActivities() != null) {
			itineraryDTO.setActivities(
					itinerary.getActivities().stream().map(activityMapper::toDTO).collect(Collectors.toList()));
		}
		if (itinerary.getTrip() != null) {
			itineraryDTO.setTripId(itinerary.getTrip().getTripId());
		}

		return itineraryDTO;
	}

	// Update existing Itinerary entity with new Itinerary details
	public Itinerary updateExistingItinerary(Itinerary existingItinerary, Itinerary updatedItinerary) {
		if (existingItinerary == null || updatedItinerary == null) {
			return null;
		}
		existingItinerary.setActivities(updatedItinerary.getActivities());
		if (updatedItinerary.getTrip() != null) {
			existingItinerary.setTrip(updatedItinerary.getTrip());
		}

		return existingItinerary;
	}
}
