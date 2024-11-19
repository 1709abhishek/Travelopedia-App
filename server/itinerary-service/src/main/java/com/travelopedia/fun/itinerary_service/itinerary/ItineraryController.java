package com.travelopedia.fun.itinerary_service.itinerary;

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
@RequestMapping("/itinerary")
public class ItineraryController {
	
	private final ItineraryService itineraryService;

	public ItineraryController(ItineraryService itineraryService) {
		super();
		this.itineraryService = itineraryService;
	}
	@GetMapping("/all")
	public ResponseEntity<List<Itinerary>> getAllItineraries(){
		final List<Itinerary> itineraries = itineraryService.findAllItineraries();
		return ResponseEntity.ok(itineraries);
	}
	@GetMapping("/{itineraryId}")
	public ResponseEntity<Itinerary> getItineraryById(@PathVariable Long itineraryId){
		return itineraryService.findItineraryById(itineraryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	public ResponseEntity<List<Itinerary>> getAllItinerariesByTripId(Long tripId){
		final List<Itinerary> itineraries = itineraryService.fetchAllItinerariesByTripId(tripId);
		return ResponseEntity.ok(itineraries);
	}
	
	@PostMapping
	public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary, Long tripId){
		final Itinerary createdItinerary = itineraryService.createOrUpdateItinerary(itinerary,tripId);
		return ResponseEntity.ok(createdItinerary);
	}
	@PutMapping("/update/{itineraryId}")
	public ResponseEntity<Itinerary> updateItinerary(@PathVariable Long itineraryId, @RequestBody Itinerary itinerary,Long tripId){
		itinerary.setItineraryId(itineraryId);
		final Itinerary updatedItinerary = itineraryService.createOrUpdateItinerary(itinerary,tripId);
		return ResponseEntity.ok(updatedItinerary);
	}
	@DeleteMapping("/delete/{itineraryId}")
	public ResponseEntity<Itinerary> deleteItinerary(@PathVariable Long itineraryId){
		itineraryService.deletItinerary(itineraryId);
		return ResponseEntity.ok().build();
	}

}
