package com.travelopedia.fun.itinerary_service.itinerary.controller;

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

import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.dto.ItineraryDTO;
import com.travelopedia.fun.itinerary_service.itinerary.service.ItineraryService;

/**
 * Controller class for managing itineraries within the Travelopedia application.
 * Handles requests related to creating, reading, updating, and deleting itineraries.
 */
@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    /**
     * Retrieves a list of all itineraries.
     * 
     * @return ResponseEntity containing a list of ItineraryDTO objects and HTTP status 200 OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ItineraryDTO>> getAllItineraries() {
        List<ItineraryDTO> itineraries = itineraryService.getAllItineraries();
        return new ResponseEntity<>(itineraries, HttpStatus.OK);
    }

    /**
     * Retrieves an itinerary by its unique ID.
     * 
     * @param itineraryId The ID of the itinerary to be retrieved.
     * @return ResponseEntity containing the ItineraryDTO object and HTTP status 200 OK.
     */
    @GetMapping("/get/{itineraryId}")
    public ResponseEntity<ItineraryDTO> getItineraryById(@PathVariable Long itineraryId) {
        ItineraryDTO itinerary = itineraryService.getItineraryById(itineraryId);
        return new ResponseEntity<>(itinerary, HttpStatus.OK);
    }

    /**
     * Creates a new itinerary associated with a specific trip.
     * 
     * @param tripId The ID of the trip to associate with the itinerary.
     * @param itinerary The Itinerary object containing the itinerary details to be created.
     * @return ResponseEntity containing the created ItineraryDTO object and HTTP status 201 Created.
     */
    @PostMapping("/save/{tripId}")
    public ResponseEntity<ItineraryDTO> createItinerary(@PathVariable Long tripId, @RequestBody Itinerary itinerary) {
        ItineraryDTO createdItinerary = itineraryService.createItinerary(tripId, itinerary);
        return new ResponseEntity<>(createdItinerary, HttpStatus.CREATED);
    }

    /**
     * Updates an existing itinerary by its unique ID.
     * 
     * @param itineraryId The ID of the itinerary to be updated.
     * @param itinerary The Itinerary object containing the updated itinerary details.
     * @return ResponseEntity containing the updated ItineraryDTO object and HTTP status 200 OK.
     */
    @PutMapping("/update/{itineraryId}")
    public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable Long itineraryId, @RequestBody Itinerary itinerary) {
        ItineraryDTO updatedItinerary = itineraryService.updateItinerary(itineraryId, itinerary);
        return new ResponseEntity<>(updatedItinerary, HttpStatus.OK);
    }

    /**
     * Deletes an itinerary by its unique ID.
     * 
     * @param itineraryId The ID of the itinerary to be deleted.
     * @return ResponseEntity with HTTP status 204 No Content and a success message.
     */
    @DeleteMapping("/delete/{itineraryId}")
    public ResponseEntity<String> deleteItinerary(@PathVariable Long itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Itinerary with id " + itineraryId + " deleted successfully.");
    }
}
