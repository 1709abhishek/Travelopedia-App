package com.travelopedia.fun.itinerary_service.itinerary.controller;

import java.util.List;
import java.util.Optional;

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

import com.travelopedia.fun.itinerary_service.dto.ResponseWrapper;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.service.ItineraryService;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {
	
	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping("/allItineraries")
	public ResponseEntity<List<Itinerary>> getAllItineraries(){
		List<Itinerary> result = itineraryService.getAllItineraries();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getItinerary/{itineraryId}")
	public ResponseEntity<Itinerary> getItinerarybyId(@PathVariable("itineraryId") int itineraryId){
		Optional<Itinerary> result = itineraryService.fetchItinerary(itineraryId);
		if (result.isPresent()) {
			return new ResponseEntity<>(result.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/createItinerary")
	public ResponseEntity<ResponseWrapper<Itinerary>> createItinerary(@RequestBody Itinerary itinerary) {
		ResponseWrapper<Itinerary> createdData = itineraryService.saveItineraryData(itinerary);
		return new ResponseEntity<>(createdData, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateItinerary")
	public ResponseEntity<ResponseWrapper<Itinerary>>  updateItinerary(@RequestBody Itinerary itinerary) {
		try {
			ResponseWrapper<Itinerary> updatedEntity = itineraryService.updateItineraryData(itinerary);
			return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/deleteItinerary/{itineraryId}")
	public ResponseEntity<ResponseWrapper<Itinerary>> deleteTour(@PathVariable("itineraryId") int itineraryId) {
		try {
			ResponseWrapper<Itinerary> response = itineraryService.deleteItineraryData(itineraryId);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>("Encountered error while deleting the itinerary",null));
	    }
	}
	

}
