package com.travelopedia.fun.itinerary_service.trip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.itinerary_service.exception.ResourceNotFoundException;
import com.travelopedia.fun.itinerary_service.trip.dto.TripDTO;
import com.travelopedia.fun.itinerary_service.trip.dto.TripWithItinerariesDTO;
import com.travelopedia.fun.itinerary_service.trip.entity.Trip;
import com.travelopedia.fun.itinerary_service.trip.services.TripService;

@RestController
@RequestMapping("/api/trips")
public class TripController {

	private final TripService tripService;

	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping
	public ResponseEntity<List<TripDTO>> getAllTrips() {
		List<TripDTO> trips = tripService.getAllTrips();
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/getTripById/{tripId}")
	public ResponseEntity<TripDTO> getTripById(@PathVariable Long tripId) {
		try {
			TripDTO tripDTO = tripService.getTripById(tripId);
			return new ResponseEntity<>(tripDTO, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/with-itineraries-activities/{tripId}")
	public ResponseEntity<TripWithItinerariesDTO> getTripWithItinerariesAndActivities(@PathVariable Long tripId) {
		try {
			TripWithItinerariesDTO tripWithItinerariesDTO = tripService.getTripWithItinerariesAndActivities(tripId);
			return new ResponseEntity<>(tripWithItinerariesDTO, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping
	public ResponseEntity<TripDTO> createTrip(@RequestBody Trip trip) {
		try {
			TripDTO createdTrip = tripService.createTrip(trip);
			return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PutMapping("/updateTrip/{tripId}")
	public ResponseEntity<TripDTO> updateTrip(@PathVariable Long tripId, @RequestBody Trip trip) {
		try {
			TripDTO updatedTrip = tripService.updateTrip(tripId, trip);
			return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@DeleteMapping("/deleteTrip/{tripId}")
	public ResponseEntity<String> deleteTrip(@PathVariable Long tripId) {
		try {
			String message = tripService.deleteTrip(tripId);
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
