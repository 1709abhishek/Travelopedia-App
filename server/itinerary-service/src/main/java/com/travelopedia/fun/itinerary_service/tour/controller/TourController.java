package com.travelopedia.fun.itinerary_service.tour.controller;

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
import com.travelopedia.fun.itinerary_service.tour.beans.Tour;
import com.travelopedia.fun.itinerary_service.tour.service.TourService;
@RestController
@RequestMapping("/tour")
public class TourController {
	
	@Autowired
	private TourService tourService;
	@GetMapping("/allTours")
	public ResponseEntity<List<Tour>> getAllTours(){
		List<Tour> result = tourService.getAllTours();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getTour/{tourId}")
	public ResponseEntity<Tour> getTourbyId(@PathVariable("tourId") int tourId){
		Optional<Tour> result = tourService.fetchTour(tourId);
		if (result.isPresent()) {
			return new ResponseEntity<>(result.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/createTour")
	public ResponseEntity<ResponseWrapper<Tour>> createTour(@RequestBody Tour tour) {
		ResponseWrapper<Tour> createdData = tourService.saveTourData(tour);
		return new ResponseEntity<>(createdData, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateTour")
	public ResponseEntity<ResponseWrapper<Tour>>  updateTour(@RequestBody Tour tour) {
		try {
			ResponseWrapper<Tour> updatedEntity = tourService.updateTourData(tour);
			return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/deleteTour/{tourId}")
	public ResponseEntity<ResponseWrapper<Tour>> deleteTour(@PathVariable("tourId") int tourId) {
		try {
			ResponseWrapper<Tour> response = tourService.deleteTourData(tourId);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>("Encountered error while deleting the tour",null));
	    }
	}

}
