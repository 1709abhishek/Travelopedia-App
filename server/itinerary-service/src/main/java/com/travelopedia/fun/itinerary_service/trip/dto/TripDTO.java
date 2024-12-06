package com.travelopedia.fun.itinerary_service.trip.dto;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

public class TripDTO {

	private Long tripId;
	private String destination;
	private String country;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String description;
	private LocalDate date;
	private int duration;

	
	public TripDTO(Long tripId, String destination, String country, LocalDate startDate, LocalDate endDate, String description,
			LocalDate date, int duration) {
		super();
		this.tripId = tripId;
		this.destination = destination;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.date = date;
		this.duration = duration;
	}

	public TripDTO() {
		// TODO Auto-generated constructor stub
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
	

}
