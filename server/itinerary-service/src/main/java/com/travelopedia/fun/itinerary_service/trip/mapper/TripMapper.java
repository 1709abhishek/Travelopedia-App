package com.travelopedia.fun.itinerary_service.trip.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.mapper.ItineraryMapper;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.dto.TripWithItinerariesDTO;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;

@Component
public class TripMapper {

	private final ItineraryMapper itineraryMapper;

	public TripMapper(ItineraryMapper itineraryMapper) {
		this.itineraryMapper = itineraryMapper;
	}

	// Convert Trip entity to TripDTO
	public TripDTO toDTO(Trip trip) {
		if (trip == null) {
			return null;
		}
		TripDTO tripDTO = new TripDTO();
		tripDTO.setTripId(trip.getTripId());
		tripDTO.setCountry(trip.getCountry());
		tripDTO.setDestination(trip.getDestination());
		tripDTO.setStartDate(trip.getStartDate());
		tripDTO.setEndDate(trip.getEndDate());
		tripDTO.setDescription(trip.getDescription());
		tripDTO.setDuration(trip.getDuration());
		tripDTO.setDate(trip.getDate());
		return tripDTO;
	}

	public TripWithItinerariesDTO tripWithItineariesToDTO(Trip trip) {
		if (trip == null) {
			return null;
		}
		TripWithItinerariesDTO tripWithItinerariesDTO = new TripWithItinerariesDTO();
		tripWithItinerariesDTO.setTripId(trip.getTripId());
		tripWithItinerariesDTO.setCountry(trip.getCountry());
		tripWithItinerariesDTO.setDescription(trip.getDescription());
		tripWithItinerariesDTO.setDestination(trip.getDestination());
		tripWithItinerariesDTO.setStartDate(trip.getStartDate());
		tripWithItinerariesDTO.setEndDate(trip.getEndDate());
		tripWithItinerariesDTO.setDuration(trip.getDuration());
		tripWithItinerariesDTO.setDate(trip.getDate());
		if (!trip.getItineraries().isEmpty()) {
			List<ItineraryDTO> itineraryDTOs = trip.getItineraries().stream().map(itineraryMapper::toDTO)
					.collect(Collectors.toList());
			tripWithItinerariesDTO.setItineraries(itineraryDTOs);
		}

		return tripWithItinerariesDTO;
	}

	// Convert TripDTO to Trip entity
	public Trip toEntity(TripDTO tripDTO) {
		if (tripDTO == null) {
			return null;
		}
		Trip trip = new Trip();
		trip.setTripId(tripDTO.getTripId());
		trip.setCountry(tripDTO.getCountry());
		trip.setDestination(tripDTO.getDestination());
		trip.setStartDate(tripDTO.getStartDate());
		trip.setEndDate(tripDTO.getEndDate());
		trip.setDescription(tripDTO.getDescription());
		return trip;
	}

	// Update existing Trip entity with new Trip details
	public Trip updateExistingTrip(Trip existingTrip, Trip updatedTrip) {
		if (updatedTrip == null || existingTrip == null) {
			return null;
		}
		existingTrip.setCountry(updatedTrip.getCountry());
		existingTrip.setDestination(updatedTrip.getDestination());
		existingTrip.setStartDate(updatedTrip.getStartDate());
		existingTrip.setEndDate(updatedTrip.getEndDate());
		existingTrip.setDescription(updatedTrip.getDescription());
		if (existingTrip.getStartDate() != null && existingTrip.getEndDate() != null) {
			long duration = ChronoUnit.DAYS.between(existingTrip.getStartDate(), existingTrip.getEndDate());
			existingTrip.setDuration((int) duration);
		}
		existingTrip.setDate(LocalDate.now());
		if (updatedTrip.getItineraries() != null) {
			existingTrip.getItineraries().clear();
			existingTrip.getItineraries().addAll(updatedTrip.getItineraries());
		}

		return existingTrip;
	}
}
