package com.travelopedia.fun.itinerary_service.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.itinerary_service.trip.beans.Trip;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.service.TripService;

/**
 * Controller class for managing trips within the Travelopedia application.
 * Handles requests related to creating, reading, updating, and deleting trips.
 */
@RestController
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripService tripService;

	/**
	 * Retrieves a list of all trips.
	 * 
	 * @return ResponseEntity containing a list of TripDTO objects and HTTP status
	 *         200 OK.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<TripDTO>> getAllTrips() {
		List<TripDTO> trips = tripService.getAllTrips();
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	/**
	 * Retrieves a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be retrieved.
	 * @return ResponseEntity containing the Trip object and HTTP status 200 OK.
	 */
	@GetMapping("/{tripId}")
	public ResponseEntity<Trip> getTripById(@PathVariable Long tripId) {
		Trip trip = tripService.getTripById(tripId);
		return new ResponseEntity<>(trip, HttpStatus.OK);
	}

	/**
	 * Creates a new trip.
	 * 
	 * @param trip The Trip object containing the trip details to be created.
	 * @return ResponseEntity containing the created TripDTO object and HTTP status
	 *         201 Created.
	 */
	@PostMapping("/save")
	public ResponseEntity<TripDTO> createTrip(@RequestBody Trip trip) {
		TripDTO createdTrip = tripService.createTrip(trip);
		return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
	}

	/**
	 * Updates an existing trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be updated.
	 * @param trip   The Trip object containing the updated trip details.
	 * @return ResponseEntity containing the updated TripDTO object and HTTP status
	 *         200 OK.
	 */
	@PutMapping("/update/{tripId}")
	public ResponseEntity<TripDTO> updateTrip(@PathVariable Long tripId, @RequestBody Trip trip) {
		TripDTO updatedTrip = tripService.updateTrip(tripId, trip);
		return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
	}

	/**
	 * Deletes a trip by its unique ID.
	 * 
	 * @param tripId The ID of the trip to be deleted.
	 * @return ResponseEntity containing a response message and HTTP status 200 OK.
	 */
	@DeleteMapping("/delete/{tripId}")
	public ResponseEntity<String> deleteTrip(@PathVariable Long tripId) {
		String responseMessage = tripService.deleteTrip(tripId);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
}
