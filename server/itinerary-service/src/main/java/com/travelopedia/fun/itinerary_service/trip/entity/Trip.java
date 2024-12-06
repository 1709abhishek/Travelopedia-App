package com.travelopedia.fun.itinerary_service.trip.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "trip")
@EqualsAndHashCode(of = "tripId")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tripId;
	private String country;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String description;
	private LocalDate date;
	private int duration;
	private String googleLink;
	// One-to-many relationship with Itinerary
	@OneToMany(mappedBy = "trip", fetch = FetchType.LAZY)
	private List<Itinerary> itineraries = new ArrayList<>();

	public Trip() {
		// TODO Auto-generated constructor stub
	}

	public Trip(Long tripId, String country, String destination, LocalDate startDate, LocalDate endDate,
			String description, LocalDate date, int duration, String googleLink, List<Itinerary> itineraries) {
		super();
		this.tripId = tripId;
		this.country = country;
		this.destination = destination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.googleLink = googleLink;
		this.itineraries = itineraries;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
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
