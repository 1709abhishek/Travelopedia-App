package com.travelopedia.fun.itinerary_service.trip.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;

public class TripWithItinerariesDTO {

    private Long tripId;
    private String destination;
    private String country;
  
	private LocalDate date;
	private int duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String description;
    private List<ItineraryDTO> itineraries;

    // Constructor to initialize all fields
    public TripWithItinerariesDTO() {
   
    }

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItineraryDTO> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<ItineraryDTO> itineraries) {
		this.itineraries = itineraries;
	}

    // Getters and Setters
    
}
