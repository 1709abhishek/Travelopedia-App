package com.travelopedia.fun.itinerary_service.itinerary.beans;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.travelopedia.fun.itinerary_service.tour.beans.Tour;

@Document(collection = "itineraries")
public class Itinerary {
	@Id
	private int itineraryId;
	private int accountId;
	private int itineraryDuration;
	private String itineraryCountry;
	private String itineraryDescription;
	private Date startDate;
	private Date endDate;
	private double totalCost;

	private List<Tour> tours;

	public Itinerary() {
		super();
	}

	public Itinerary(int itineraryId, int accountId, int itineraryDuration, String itineraryCountry,
			String itineraryDescription, Date startDate, Date endDate, double totalCost, List<Tour> tours) {
		super();
		this.itineraryId = itineraryId;
		this.accountId = accountId;
		this.itineraryDuration = itineraryDuration;
		this.itineraryCountry = itineraryCountry;
		this.itineraryDescription = itineraryDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.tours = tours;
	}

	public int getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(int itineraryId) {
		this.itineraryId = itineraryId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getItineraryDuration() {
		return itineraryDuration;
	}

	public void setItineraryDuration(int itineraryDuration) {
		this.itineraryDuration = itineraryDuration;
	}

	public String getItineraryCountry() {
		return itineraryCountry;
	}

	public void setItineraryCountry(String itineraryCountry) {
		this.itineraryCountry = itineraryCountry;
	}

	public String getItineraryDescription() {
		return itineraryDescription;
	}

	public void setItineraryDescription(String itineraryDescription) {
		this.itineraryDescription = itineraryDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	@Override
	public String toString() {
		return "Itinerary [itineraryId=" + itineraryId + ", accountId=" + accountId + ", itineraryDuration="
				+ itineraryDuration + ", itineraryCountry=" + itineraryCountry + ", itineraryDescription="
				+ itineraryDescription + ", startDate=" + startDate + ", endDate=" + endDate + ", totalCost="
				+ totalCost + ", tours=" + tours + "]";
	}

}
