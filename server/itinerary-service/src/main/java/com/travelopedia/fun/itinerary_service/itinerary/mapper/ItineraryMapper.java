package com.travelopedia.fun.itinerary_service.itinerary.mapper;

import org.springframework.stereotype.Component;

import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
@Component
public class ItineraryMapper {
	
	 public ItineraryDTO toDTO(Itinerary itinerary) {
		 if (itinerary == null) {
	            return null;
	        }
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setItineraryId(itinerary.getItineraryId());
		itineraryDTO.setTime(itinerary.getTime());
		itineraryDTO.setPlace(itinerary.getPlace());
		itineraryDTO.setGoogleLink(itinerary.getGoogleLink());
        return itineraryDTO;

	 	}
	 
	 public Itinerary toEntity(ItineraryDTO itineraryDTO) {
	        if (itineraryDTO == null) {
	            return null;
	        }
	        Itinerary itinerary = new Itinerary();
//	        itinerary.setItineraryId(itineraryDTO.getItineraryId());
	        itinerary.setTime(itineraryDTO.getTime());
	        itinerary.setPlace(itineraryDTO.getPlace());
	        itinerary.setGoogleLink(itineraryDTO.getGoogleLink());
	        return itinerary;
	    }
	 
	 public void updateExistingItinerary(Itinerary existingItinerary, Itinerary newItinerary) {
	        if (newItinerary.getTime() != null) {
	            existingItinerary.setTime(newItinerary.getTime());
	        }
	        if (newItinerary.getPlace() != null) {
	            existingItinerary.setPlace(newItinerary.getPlace());
	        }
	        if (newItinerary.getGoogleLink() != null) {
	            existingItinerary.setGoogleLink(newItinerary.getGoogleLink());
	        }
	    }
}