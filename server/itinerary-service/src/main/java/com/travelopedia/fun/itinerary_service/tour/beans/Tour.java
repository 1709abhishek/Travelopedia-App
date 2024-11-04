package com.travelopedia.fun.itinerary_service.tour.beans;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tours")
public class Tour {
	@Id
	private int tourId;
	private String name;
	private String description;
	private List<String> places;
	private double price;
	private int duration;
	private Date date;
	private String category; // nature visit, adventures etc.

	public Tour() {
		super();
	}

	public Tour(int tourId, String name, String description, List<String> places, double price, int duration, Date date,
			String category) {
		super();
		this.tourId = tourId;
		this.name = name;
		this.description = description;
		this.places = places;
		this.price = price;
		this.duration = duration;
		this.date = date;
		this.category = category;
	}

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPlaces() {
		return places;
	}

	public void setPlaces(List<String> places) {
		this.places = places;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Tour [tourId=" + tourId + ", name=" + name + ", description=" + description + ", places=" + places
				+ ", price=" + price + ", duration=" + duration + ", date=" + date + ", category=" + category + "]";
	}

}