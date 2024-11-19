package com.travelopedia.fun.itinerary_service.itinerary;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Embeddable
public class Activity {
	private LocalTime time;
    private String place;
    private String googleLink;

    public Activity() {}

    public Activity(String place, String googleLink) {
        this.place = place;
        this.googleLink = googleLink;
    }
}

