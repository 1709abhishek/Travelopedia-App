package com.travelopedia.fun.itinerary_service.activity.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "activity")
@EqualsAndHashCode(of = "itineraryId")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long activityId;
	private String time;
	private String place;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itinerary_id", nullable = false)
	private Itinerary itinerary;

	public Activity() {
		super();
	}

	public Activity(Long activityId, String time, String place, Itinerary itinerary) {
		super();
		this.activityId = activityId;
		this.time = time;
		this.place = place;
		this.itinerary = itinerary;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

}
