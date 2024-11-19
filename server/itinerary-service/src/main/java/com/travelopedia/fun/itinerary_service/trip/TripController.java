package com.travelopedia.fun.itinerary_service.trip;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {

	private final TripService tripService;

	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Trip>> getAllTrips() {
		List<Trip> trips = tripService.findAllTrips();
		return ResponseEntity.ok(trips);
	}

	@GetMapping("/{tripId}")
	public ResponseEntity<Trip> getTripById(@PathVariable Long tripId) {
		return tripService.findTripById(tripId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
		Trip createdTrip = tripService.createOrUpdateTrip(trip);
		return ResponseEntity.ok(createdTrip);
	}

	@PutMapping("/update/{tripId}")
	public ResponseEntity<Trip> updateTrip(@PathVariable Long tripId, @RequestBody Trip trip) {
		trip.setTripId(tripId);
		Trip updatedTrip = tripService.createOrUpdateTrip(trip);
		return ResponseEntity.ok(updatedTrip);
	}

	@DeleteMapping("/delete/{tripId}")
	public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
		tripService.deleteTrip(tripId);
		return ResponseEntity.ok().build();
	}
}
