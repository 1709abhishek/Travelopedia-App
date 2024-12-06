package com.travelopedia.fun.itinerary_service.itinerary.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.entity.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.services.ItineraryService;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

	private final ItineraryService itineraryService;

	public ItineraryController(ItineraryService itineraryService) {
		this.itineraryService = itineraryService;
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping
	public ResponseEntity<List<ItineraryDTO>> getAllItineraries() {
		List<ItineraryDTO> itineraries = itineraryService.getAllItineraries();
		return new ResponseEntity<>(itineraries, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/{itineraryId}")
	public ResponseEntity<Itinerary> getItineraryById(@RequestParam Long tripId, @PathVariable Long itineraryId) {
		Itinerary itinerary = itineraryService.getItineraryByTripAndItineraryId(tripId, itineraryId);
		return new ResponseEntity<>(itinerary, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/trip/{tripId}")
	public ResponseEntity<List<ItineraryDTO>> getAllItinerariesByTripId(@PathVariable Long tripId) {
		List<ItineraryDTO> itineraries = itineraryService.getAllItinerariesByTripId(tripId);
		return new ResponseEntity<>(itineraries, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping
	public ResponseEntity<ItineraryDTO> createItinerary(@RequestParam Long tripId, @RequestBody Itinerary itinerary) {
		ItineraryDTO createdItinerary = itineraryService.createItinerary(tripId, itinerary);
		return new ResponseEntity<>(createdItinerary, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PutMapping("/{itineraryId}")
	public ResponseEntity<ItineraryDTO> updateItinerary(@RequestParam Long tripId, @PathVariable Long itineraryId,
			@RequestBody Itinerary itinerary) {
		ItineraryDTO updatedItinerary = itineraryService.updateItineray(tripId, itineraryId, itinerary);
		return new ResponseEntity<>(updatedItinerary, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@DeleteMapping("/{itineraryId}")
	public ResponseEntity<String> deleteItinerary(@RequestParam Long tripId, @PathVariable Long itineraryId) {
		String message = itineraryService.deleteItinerary(tripId, itineraryId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
