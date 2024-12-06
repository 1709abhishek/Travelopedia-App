package com.travelopedia.fun.itinerary_service.itinerary.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelopedia.fun.itinerary_service.activity.entity.Activity;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "itinerary")
@EqualsAndHashCode(of = "itineraryId")
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Itinerary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itineraryId;
	@OneToMany(mappedBy = "itinerary", fetch = FetchType.LAZY)
	private List<Activity> activities;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id", nullable = false)
	private Trip trip;

	public Itinerary(Long itineraryId, List<Activity> activities, Trip trip) {
		super();
		this.itineraryId = itineraryId;
		this.activities = activities;
		this.trip = trip;
	}

	public Itinerary() {
		// TODO Auto-generated constructor stub
	}

	public Long getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Long itineraryId) {
		this.itineraryId = itineraryId;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
