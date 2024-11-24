package com.travelopedia.fun.itinerary_service.itinerary.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.travelopedia.fun.itinerary_service.utils.TimeParserSerializer;

public class ItineraryDTO {

	private Long itineraryId;
	@JsonSerialize(using = TimeParserSerializer.class)
	private LocalTime time;
	private String place;
	private String googleLink;

	public Long getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Long itineraryId) {
		this.itineraryId = itineraryId;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGoogleLink() {
		return googleLink;
	}

	public void setGoogleLink(String googleLink) {
		this.googleLink = googleLink;
	}

}
